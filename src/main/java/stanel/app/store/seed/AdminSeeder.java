package stanel.app.store.seed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import stanel.app.store.company.Company;
import stanel.app.store.company.CompanyRepository;
import stanel.app.store.profile.Profile;
import stanel.app.store.profile.ProfileRepository;
import stanel.app.store.user.User;
import stanel.app.store.user.enums.Role;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final AdminSeedProperties props;
    private final ProfileRepository profileRepository;
    private final CompanyRepository companyRepository; // Optional: include if managing companies

    public AdminSeeder(AdminSeedProperties props,
            ProfileRepository profileRepository,
            CompanyRepository companyRepository) {
        this.props = props;
        this.profileRepository = profileRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) {
        if (!props.isEnabled()) {
            return;
        }

        // Check if admin already exists by email
        boolean adminExists = profileRepository.findAll().stream()
                .anyMatch(p -> p.getUser() != null && props.getEmail().equalsIgnoreCase(p.getUser().getEmail()));

        if (adminExists) {
            System.out.println(">> Admin seed skipped: Admin user already exists.");
            return;
        }

        // Split "Super Admin" into first and last name
        String[] nameParts = props.getName().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "Admin";

        // Optional: Find or create the company if companyName is provided
        // Find or create the company if companyName is provided
        Company company = null;
        if (props.getCompanyName() != null && !props.getCompanyName().isEmpty()) {
            company = companyRepository.findByName(props.getCompanyName())
                    .orElseGet(() -> {
                        Company newCompany = new Company();
                        newCompany.setName(props.getCompanyName()); // Adjust setter if your constructor differs
                        return companyRepository.save(newCompany);
                    });
        }

        // Create User entity
        // Note: If you use BCryptPasswordEncoder, encode props.getPassword() here
        User adminUser = new User(
                props.getEmail(),
                props.getPassword(),
                Role.ADMIN);

        // Create Profile entity
        Profile adminProfile = new Profile(
                firstName,
                lastName,
                props.getPhoneNumber(),
                props.getGender(),
                adminUser,
                Role.ADMIN,
                null,
                company);

        profileRepository.save(adminProfile);
        System.out.println(">> Admin user seeded successfully from YAML configuration: " + props.getEmail());
    }
}