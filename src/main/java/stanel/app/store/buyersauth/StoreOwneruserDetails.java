package stanel.app.store.buyersauth;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import stanel.app.store.buyers.StoreOwner;

public class StoreOwnerUserDetails implements UserDetails {
    private final StoreOwner storeOwner;

    public StoreOwnerUserDetails(StoreOwner storeOwner) {
        this.storeOwner = storeOwner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + storeOwner.getRole());
        return List.of(authority);
    }

    @Override
    public @Nullable String getPassword() {
        return storeOwner.getPassword();
    }

    @Override
    public String getUsername() {
        return storeOwner.getEmail();
    }

}
