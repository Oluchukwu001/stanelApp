
package stanel.app.store.seed;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import stanel.app.store.company.Company;
import stanel.app.store.company.CompanyRepository;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.user.User;
import stanel.app.store.user.UserRepository;
import stanel.app.store.user.enums.Role;

@Component
@Slf4j
public class AdminSeeder implements ApplicationRunner {

    private final AdminSeedProperties props;
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public AdminSeeder(
            AdminSeedProperties props,
            UserRepository userRepository,
            ProfileRepository profileRepository,
            PasswordEncoder passwordEncoder,
            CompanyRepository companyRepository) {
        this.props = props;
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {

        if (!props.enabled()) {
            log.info("Admin seeding disabled");
            return;
        }

        if (userRepository.existsByEmail(props.email())) {
            log.info("Admin already exists, skipping seed: {}", props.email());
            return;
        }

        Company company = companyRepository.findByName(props.companyName())
                .orElseGet(() -> {
                    Company newCompany = new Company();
                    newCompany.setName(props.companyName());
                    return companyRepository.save(newCompany);
                });

        User admin = new User();
        admin.setEmail(props.email());
        admin.setPassword(passwordEncoder.encode(props.password()));
        admin.setRole(Role.ADMIN);

        User savedAdmin = userRepository.save(admin);

        Profile profile = new Profile();
        profile.setFirstName(props.name());
        profile.setLastName("");
        profile.setPhoneNumber(props.phoneNumber());
        profile.setGender(props.gender());
        profile.setUser(savedAdmin);
        profile.setRole(Role.ADMIN);
        profile.setCompany(company);

        profileRepository.save(profile);

        log.info("Seeded admin: {}", props.email());
        log.info("Seeding admin with password length {}", props.password().length());
    }
}
