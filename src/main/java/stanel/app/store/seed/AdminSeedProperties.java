package stanel.app.store.seed;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "seed.admin")
@Getter
@Setter
public class AdminSeedProperties {
    private boolean enabled;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String password;
    private String companyName;
}