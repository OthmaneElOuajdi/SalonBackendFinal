package com.salon.coiffure.entity;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant une catégorie de services.
 * Permet d'organiser les services par type (Coupe, Coloration, Soins, etc.).
 */
@Entity
@Table(name = "service_categories", indexes = {
        @Index(name = "idx_category_name", columnList = "name")
})
@Schema(description = "Catégorie de services du salon")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la catégorie")
    private Long id;

    @Schema(description = "Nom de la catégorie (ex: Coupe, Coloration, Soins)")
    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;

    @Schema(description = "Description de la catégorie")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "Ordre d'affichage de la catégorie")
    @Column(name = "display_order")
    @Builder.Default
    private int displayOrder = 0;

    @Schema(description = "Indique si la catégorie est active")
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Schema(description = "Services appartenant à cette catégorie")
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Service> services = new HashSet<>();
}
