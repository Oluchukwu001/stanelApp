package stanel.app.store.secure;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import stanel.app.store.user.User;
import stanel.app.store.user.dtos.Response;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailService userDetailService;
    private final JwtService jwtService;

    public LoginService(PasswordEncoder passwordEncoder, UserDetailService userDetailService, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
        this.jwtService = jwtService;
    }

    public Response login(LoginRequestDTO requestDTO) {
        UserDetails userDetails = fetchUserDetailsByEmail(requestDTO.email());
        if (!passwordEncoder.matches(requestDTO.password(), userDetails.getPassword())) {
            throw new BadCredentialsException(" Invalid password or email ");
        }
        User user = ((UserDetail) userDetails).getUser();
        String token = generateToken(user);
        LoginResponseDTO login = new LoginResponseDTO(token, user.getId());
        return new Response(login, "Login successfully");
    }

    private UserDetails fetchUserDetailsByEmail(String email) {
        return userDetailService.loadUserByUsername(email);
    }

    private String generateToken(User user) {
        return jwtService.generateToken(user);
    }

}
