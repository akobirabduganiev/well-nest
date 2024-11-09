package tech.nuqta.wellnest.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nuqta.wellnest.common.ResponseMessage;
import tech.nuqta.wellnest.request.CreateProfileRequest;
import tech.nuqta.wellnest.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/create-profile")
    public ResponseMessage createProfile(@Valid @RequestBody CreateProfileRequest request) {
        return userService.createProfile(request);
    }
}
