package stanel.app.store.store;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import stanel.app.store.cart.Cart;
import stanel.app.store.order.Order;

import stanel.app.store.order.OrderResponseDTO;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.user.enums.Role;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final ProfileRepository profileRepository;

    public StoreService(StoreRepository storeRepository, ProfileRepository profileRepository) {
        this.storeRepository = storeRepository;
        this.profileRepository = profileRepository;
    }

    // CREATE STORE AND SETTING CORRESPONDING ATTRIBUTES
    public Store createStore(StoreRequestDTO requestDTO) throws BadRequestException {
        validateStoreRequestDto(requestDTO);
        Profile storeOwner = ServiceUtil.findOrThrow(profileRepository, requestDTO.storeOwnerId(), "StoreOwner");
        if (!storeOwner.getRole().equals(Role.STOREOWNER)) {
            throw new BadRequestException("This user is not a store owner");
        }
        Store store = new Store();
        Cart cart = new Cart();
        store.setName(requestDTO.name());
        store.setAddress(requestDTO.address());
        store.setEmail(requestDTO.email());
        store.setPhoneNumber(requestDTO.phoneNumber());
        store.setStoreOwner(storeOwner);
        store.setCart(cart);
        return storeRepository.save(store);

    }

    // TO UPDATE STORE ATTRIBUTES AND VALIDATE INPUT(REQUEST DTO)
    public Store updateStore(Long id, StoreRequestDTO requestDTO) throws BadRequestException {
        ValidationUtil.requirePositive(id, "Id must be positive");
        ValidationUtil.requirePositive(requestDTO.storeOwnerId(), "Store owner id must be positive");
        Profile storeOwner = ServiceUtil.findOrThrow(profileRepository, requestDTO.storeOwnerId(), "StoreOwner");
        if (!storeOwner.getRole().equals(Role.STOREOWNER)) {
            throw new BadRequestException("This user is not a store owner");
        }
        Store store = ServiceUtil.findOrThrow(storeRepository, id, "Store");
        ServiceUtil.setIfPresent(requestDTO.name(), store::setName);
        if (!requestDTO.email().isBlank() && !requestDTO.email().isEmpty()) {
            ValidationUtil.requireValidEmail(requestDTO.email(), "Enter a valid email");
        }
        ServiceUtil.setIfPresent(requestDTO.email(), store::setEmail);
        ServiceUtil.setIfPresent(requestDTO.address(), store::setAddress);
        ServiceUtil.setIfPresent(requestDTO.phoneNumber(), store::setPhoneNumber);
        store.setStoreOwner(storeOwner);
        return storeRepository.save(store);

    }

    // TO DELETE STORE BY ID
    public String deleteStoreById(Long id) {
        if (!storeRepository.existsById(id)) {
            return "Store with id " + id + " not found";
        }
        storeRepository.deleteById(id);
        return "Store with id " + id + " successfully deleted";
    }

    // GET STORE BY ID
    public Store getStoreById(Long id) {
        return ServiceUtil.findOrThrow(storeRepository, id, "Store");
    }

    // TO GET ALL STORE WITH PAGINATION
    public Page<Store> getAllStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return storeRepository.findAll(pageable);
    }

    // FIND ALL ORDERS BELONG TO A PARTICULAR STORE
    public Page<OrderResponseDTO> getAllStoreOrdersByStoreId(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = storeRepository.findOrdersByStoreIdAndOrderBycurrent(id, pageable);
        return orders.map(OrderResponseDTO::mapToDTO);

    }

    // TO VALIDATE STORE REQUEST DTO
    private void validateStoreRequestDto(StoreRequestDTO requestDTO) {
        ValidationUtil.requireNonBlank(requestDTO.name(), "Name is required");
        ValidationUtil.requireNonBlank(requestDTO.address(), "Address is required ");
        ValidationUtil.requireNonBlank(requestDTO.phoneNumber(), "Phone number is required");
        ValidationUtil.requireValidEmail(requestDTO.email(), "Enter a valid email");
        ValidationUtil.requireNonBlank(requestDTO.email(), "Email is required");
        ValidationUtil.requirePositive(requestDTO.storeOwnerId(), "Store owner is must be positive");
    }

}
