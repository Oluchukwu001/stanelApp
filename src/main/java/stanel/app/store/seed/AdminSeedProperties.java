package stanel.app.store.seed;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app.seed.admin")
public record AdminSeedProperties(
    boolean enabled,
    String name,
    String email,
    String phoneNumber,
    String gender,
    String password,
    String companyName
) {}