package com.salon.coiffure.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant un service proposé par le salon de coiffure.
 */
@Entity
@Table(name = "services", indexes = {
        @Index(name = "idx_service_name", columnList = "name")
})
@Schema(description = "Représente un service du salon de coiffure (ex: coupe, coloration, brushing)")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique du service")
    private Long id;

    @Schema(description = "Nom du service (ex: Coupe femme, Coloration, Brushing)")
    @Column(nullable = false, length = 100)
    private String name;

    @Schema(description = "Prix du service en euros")
    @Column(nullable = false)
    private double price;

    @Schema(description = "Nombre de points de fidélité offerts lors de ce service")
    @Column(name = "loyalty_points_reward", nullable = false)
    @Builder.Default
    private int loyaltyPointsReward = 0;

    @Schema(description = "Description détaillée du service")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Schema(description = "Durée du service en minutes")
    @Column(nullable = false)
    private int duration;

    @Schema(description = "Indique si le service est actif et disponible à la réservation")
    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Schema(description = "Catégorie du service")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ServiceCategory category;
}
