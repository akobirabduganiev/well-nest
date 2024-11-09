// Recipe.java
package tech.nuqta.wellnest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The Recipe class represents a recipe in the WellNest application.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Recipe extends BaseEntity {

    @Column(nullable = false)
    private String name; // Recipe name

    private String description; // Brief description

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(length = 10000)
    private String ingredients; // List of ingredients

    @Column(length = 10000)
    private String instructions; // Step-by-step cooking instructions

    private Integer calories;

    private Integer protein; // in grams

    private Integer fat; // in grams

    private Integer carbs; // in grams

    private String imageUrl; // URL to the recipe image

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals;
}
