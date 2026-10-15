// package stanel.app.store.securityPractice;

// import java.util.Collection;
// import java.util.List;

// import org.jspecify.annotations.Nullable;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import stanel.app.store.staff.Staff;

// public class StaffUserDetail implements UserDetails {
//     private final Staff staff;
//     public StaffUserDetail(Staff staff){
//         this.staff=staff;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {

//         return List.of(new SimpleGrantedAuthority("ROLE_"+staff.getRole()));
//     }

//     @Override
//     public @Nullable String getPassword() {
//         return staff.getPassword();
//     }

//     @Override
//     public String getUsername() {
//        return staff.getEmail();
//     }
    
// }
