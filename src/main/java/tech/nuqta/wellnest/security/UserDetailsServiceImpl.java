package tech.nuqta.wellnest.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.nuqta.wellnest.exception.ItemNotFoundException;
import tech.nuqta.wellnest.repository.UserRepository;

/**
 * Implementation of the UserDetailsService interface for loading user details based on the username.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new ItemNotFoundException("User not found"));
    }
}
