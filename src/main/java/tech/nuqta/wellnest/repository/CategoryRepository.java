package tech.nuqta.wellnest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nuqta.wellnest.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findByName(String name);
}