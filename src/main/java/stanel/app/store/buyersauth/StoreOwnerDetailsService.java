package stanel.app.store.buyersauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;
import stanel.app.store.buyers.StoreOwner;
import stanel.app.store.buyers.StoreOwnerRepository;

@RequiredArgsConstructor 
public class StoreOwnerDetailsService implements UserDetailsService{
    private final StoreOwnerRepository storeOwnerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StoreOwner storeOwner = storeOwnerRepository.findStoreOwnerByEmail(username).orElseThrow(()->new UsernameNotFoundException("User with username "+username+" not found"));
        StoreOwnerUserDetails userDetails = new StoreOwnerUserDetails(storeOwner);
        return userDetails;
    }
    
}
