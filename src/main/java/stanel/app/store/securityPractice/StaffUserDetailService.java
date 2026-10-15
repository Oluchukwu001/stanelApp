// package stanel.app.store.securityPractice;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;
// import stanel.app.store.staff.Staff;
// import stanel.app.store.staff.StaffRepository;

// @Service 
// @RequiredArgsConstructor 
// public class StaffUserDetailService implements UserDetailsService{
//     private final StaffRepository staffRepository;
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Staff staff = staffRepository.findStaffByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//         return new StaffUserDetail(staff);
//     }
    
// }
