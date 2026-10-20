package stanel.app.store.secure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import stanel.app.store.user.dtos.Response;

@RestController
@RequestMapping("/api/v1/user-login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response login(
            @Valid @RequestBody LoginRequestDTO requestDTO) {

        return loginService.login(requestDTO);
    }
}
