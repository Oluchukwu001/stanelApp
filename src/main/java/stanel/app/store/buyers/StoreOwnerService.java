package stanel.app.store.buyers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import stanel.app.store.exception.NotFoundException;
import stanel.app.store.store.Store;
import stanel.app.store.store.StoreRepository;
import stanel.app.store.user.User;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
@RequiredArgsConstructor
public class StoreOwnerService {
    private final StoreOwnerRepository storeOwnerRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StoreOwner createStoreOwner(StoreOwnerRequestDTO requestDTO) {
        validateStoreOwnerRequestDTO(requestDTO);
        List<Store> stores = fetchStores(requestDTO);
        StoreOwner storeOwner = storeOwnerEntity(requestDTO);
        User user = creatUser(requestDTO);
        storeOwner.setUser(user);
        if (!stores.isEmpty() && stores != null) {
            storeOwner.setStores(stores);
        }
        return storeOwnerRepository.save(storeOwner);
    }

    @Transactional
    public StoreOwner updateStoreOwner(Long id, StoreOwnerRequestDTO requestDTO) {
        ValidationUtil.requirePositive(id, "ID must be positive");
        List<Store> store =new ArrayList<>();
        if (requestDTO.storeIds() != null) {
            store = fetchStores(requestDTO);
        }
        StoreOwner storeOwner = fetchStoreOwner(id);
        User user = updateUserAndValidateEmailAndSetEmailToUser(storeOwner,requestDTO);
        ServiceUtil.setIfPresent(requestDTO.name(), storeOwner::setName);
        ServiceUtil.setIfPresent(requestDTO.phoneNumber(), storeOwner::setPhoneNumber);
        ServiceUtil.setIfPresent(store, storeOwner::setStores);
        ServiceUtil.setIfPresent(requestDTO.gender(), storeOwner::setGender);
        if (user != null) {
            storeOwner.setUser(user);
        }

        return storeOwnerRepository.save(storeOwner);
    }

    @Transactional(readOnly = true)
    public StoreOwner getStoreOwnerById(Long id) {
        return ServiceUtil.findOrThrow(storeOwnerRepository, id, "StoreOwner");
    }

    @Transactional
    public String deleteStoreOwnerById(Long id) {
        if (!storeOwnerRepository.existsById(id)) {
            throw new NotFoundException("Store owner with ID " + id + " not found");
        }
        storeOwnerRepository.deleteById(id);
        return "Store owner with ID " + id + " deleted successfully";
    }

    @Transactional(readOnly = true)
    public Page<StoreOwner> getAllStoreOwner(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return storeOwnerRepository.findAll(pageable);
    }
    @Transactional 
    public String updatePassword(Long id, String password) {
        StoreOwner storeOwner = fetchStoreOwner(id);
        User user = storeOwner.getUser();
        user.setPassword(passwordEncoder.encode(password));
        storeOwner.setUser(user);
        storeOwnerRepository.save(storeOwner);
        return "password successfully updated";
    }

    private void validateEmailAndSetToUser(User user, String email) {
        if (!email.isBlank()) {
            ValidationUtil.requireValidEmail(email, "Enter valid email");
            ServiceUtil.setIfPresent(email, user::setEmail);
        }
    }

    private StoreOwner storeOwnerEntity(StoreOwnerRequestDTO requestDTO) {
        StoreOwner storeOwner = new StoreOwner();
        storeOwner.setGender(requestDTO.gender());
        storeOwner.setName(requestDTO.name());
        storeOwner.setPhoneNumber(requestDTO.phoneNumber());
        return storeOwner;
    }

    private User creatUser(StoreOwnerRequestDTO requestDTO) {
        User user = new User();
        user.setEmail(requestDTO.email());
        user.setPassword(passwordEncoder.encode(requestDTO.password()));
        user.setRole(requestDTO.role());
        return user;
    }
    private  void validateEmail(String email){
        if(email !=null){
            
        }
    }

    private StoreOwner fetchStoreOwner(Long id) {
        return ServiceUtil.findOrThrow(storeOwnerRepository, id, "Store owner");
    }

    private List<Store> fetchStores(StoreOwnerRequestDTO requestDTO) {
        return requestDTO.storeIds().stream().filter(Objects::nonNull)
                .map(el -> ServiceUtil.findOrThrow(storeRepository, el, "Store")).toList();

    }

    private void validateStoreOwnerRequestDTO(StoreOwnerRequestDTO requestDTO) {
        ValidationUtil.requireNonBlank(requestDTO.name(), "Name is required");
        ValidationUtil.requireNonBlank(requestDTO.phoneNumber(), "Phone number is required");
        ValidationUtil.requireNonBlank(requestDTO.email(), "Email is required");
        ValidationUtil.requireValidEmail(requestDTO.email(), "Enter a valid email");
    }

    private User updateUserAndValidateEmailAndSetEmailToUser(StoreOwner storeOwner,StoreOwnerRequestDTO requestDTO) {
        User user = storeOwner.getUser();
        validateEmailAndSetToUser(user, requestDTO.email());
        ServiceUtil.setIfPresent(requestDTO.role(), user::setRole);
        return user;
    }

}
