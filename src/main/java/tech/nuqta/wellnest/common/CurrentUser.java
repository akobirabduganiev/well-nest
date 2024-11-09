package tech.nuqta.wellnest.common;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tech.nuqta.wellnest.entity.User;

public class CurrentUser {
    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }

        return (User) authentication.getPrincipal();
    }
}