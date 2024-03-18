package br.com.ek.jwt.controller;

import br.com.ek.jwt.controller.dto.LoginInput;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginInput loginInput) {
        var authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginInput.username(), loginInput.password());
        var authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        return authenticationResponse.getAuthorities().toString();
    }
}
