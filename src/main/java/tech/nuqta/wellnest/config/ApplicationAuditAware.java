package tech.nuqta.wellnest.config;


import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tech.nuqta.wellnest.entity.User;

import java.util.Optional;

/**
 * The ApplicationAuditAware class is responsible for providing the current auditor for auditing purposes.
 * It implements the AuditorAware interface and specifies the type Long as the auditor type.
 */
public class ApplicationAuditAware implements AuditorAware<Long> {
    @NotNull
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        User userPrincipal = (User) authentication.getPrincipal();

        return Optional.ofNullable(userPrincipal.getId());
    }
}