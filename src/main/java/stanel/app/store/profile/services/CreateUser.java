package stanel.app.store.profile.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import stanel.app.store.company.Company;
import stanel.app.store.company.CompanyRepository;
import stanel.app.store.exception.BadRequestException;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRespository;
import stanel.app.store.profile.dto.ProfileRequestDTO;
import stanel.app.store.user.User;
import stanel.app.store.user.UserRepository;
import stanel.app.store.util.ServiceUtil;
import stanel.app.store.util.ValidationUtil;

@Service
@RequiredArgsConstructor
public class CreateUser {
    private final UserRepository userRepository;
    private final ProfileRespository profileRespository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    // Get user details
    public Profile execute(ProfileRequestDTO requestDTO){
        // Validate Request 
        validateDTO(requestDTO);
        
        // Check if Email or Phone Number exist 
        boolean userExist = userRepository.existsByEmail(requestDTO.email()) || userRepository.existsByPhoneNumber(requestDTO.phoneNumber());
        if(userExist)
            throw new BadRequestException("User already exsts");
        
        // Hash password 
        String hashedPassword = passwordEncoder.encode(requestDTO.password());
        
        // Create user 
        User user = new User(requestDTO.email(), hashedPassword, requestDTO.role());


        // create profile and link user and company
        Profile profile = new Profile(requestDTO.firstName(), requestDTO.lastName(), requestDTO.phoneNumber(),requestDTO.gender(), user, user.getRole(), List.of(),fetchCompany(requestDTO.companyID()));
        
       
        // Save User  
        return profileRespository.save(profile);
    }

    private void validateDTO(ProfileRequestDTO requestDTO) {
        ValidationUtil.requireNonBlank(requestDTO.email(), "Email is requird");
        ValidationUtil.requireNonBlank(requestDTO.phoneNumber(), "Phone number is requird");
        ValidationUtil.requireNonBlank(requestDTO.firstName(), "First name is requird");
        ValidationUtil.requireNonBlank(requestDTO.lastName(), "Last name is requird");
        ValidationUtil.requireValidEmail(requestDTO.email(), "Invalid Email");
        ValidationUtil.requireMinLength(requestDTO.password(), 8, "Password should be at least 8 characters");
    }

    private Company fetchCompany(Long companyID){
        return ServiceUtil.findOrThrow(companyRepository, companyID, "Company");
       
    }

}
