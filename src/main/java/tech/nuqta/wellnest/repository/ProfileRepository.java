package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}