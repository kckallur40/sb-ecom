package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank(message = "ERROR: category name must not be blank...")
    @Size(min = 5, message = "ERROR: category name must contain minimum 5 characters...")
    private String categoryName;
}
