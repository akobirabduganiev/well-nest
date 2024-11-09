// Category.java
package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The Category class represents a recipe category in the WellNest application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name; // e.g., Low-Calorie, High-Protein

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes;
}
