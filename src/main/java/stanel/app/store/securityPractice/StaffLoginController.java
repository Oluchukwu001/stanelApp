// package stanel.app.store.securityPractice;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import lombok.RequiredArgsConstructor;
// import stanel.app.store.exception.NotFoundException;
// import stanel.app.store.staff.Staff;
// import stanel.app.store.staff.StaffRepository;

// @RestController 
// @RequiredArgsConstructor 
// @RequestMapping ()
// public class StaffLoginController {
//     private final PasswordEncoder passwordEncoder;
//     private final AuthenticationManager authenticationManager;
//     private final StaffRepository staffRepository;
//     private final JwtService jwtService;

//     public StaffLoginResponse staffLogin(@RequestBody StaffLoginRequest request){
//         Staff staff = staffRepository.findStaffByEmail(request.email()).orElseThrow(()-> new NotFoundException("Staff not found"));
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), staff.getPassword()));
//         return createStaffLoginResponse(staff);
//     }
    
//     private StaffLoginResponse createStaffLoginResponse(Staff staff){
//         return new StaffLoginResponse(jwtService.generateToken(staff),staff.getId());
//     }
// }
