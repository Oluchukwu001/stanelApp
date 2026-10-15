package stanel.app.store.secure;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;
import stanel.app.store.user.User;

@RequiredArgsConstructor 
public class UserDetail implements UserDetails{
    private final User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole());
        return List.of(authority);
    }

    @Override
    public @Nullable String getPassword() {
       return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public User getUser(){
        return user;
    }
    
}
