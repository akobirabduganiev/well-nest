package tech.nuqta.wellnest.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.nuqta.wellnest.common.ResponseMessage;
import tech.nuqta.wellnest.request.AuthenticationRequest;
import tech.nuqta.wellnest.request.RegistrationRequest;
import tech.nuqta.wellnest.response.AuthenticationResponse;
import tech.nuqta.wellnest.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(
            @Valid @RequestBody RegistrationRequest request
    )  {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/activate")
    public ResponseEntity<AuthenticationResponse> activate(
            @RequestParam("token") String token
    ) throws MessagingException {
        return ResponseEntity.ok(authenticationService.activateAccount(token));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestParam("refresh_token") String refreshToken
    ) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}
