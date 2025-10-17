package com.salon.coiffure.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité représentant les horaires d'ouverture du salon par jour de la semaine.
 */
@Entity
@Table(name = "working_hours", uniqueConstraints = {
        @UniqueConstraint(name = "uk_workinghours_day", columnNames = { "day_of_week" })
})
@Schema(description = "Horaires d'ouverture du salon par jour")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identifiant unique de la ligne d'horaire")
    private Long id;

    @Schema(description = "Jour de la semaine")
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false, length = 16)
    private DayOfWeek dayOfWeek;

    @Schema(description = "Heure d'ouverture (null si fermé)")
    @Column(name = "start_time")
    private LocalTime startTime;

    @Schema(description = "Heure de fermeture (null si fermé)")
    @Column(name = "end_time")
    private LocalTime endTime;

    @Schema(description = "Indique si le salon est fermé ce jour-là")
    @Column(nullable = false)
    @Builder.Default
    private boolean closed = false;

    /**
     * Validation des horaires avant la persistance ou la mise à jour.
     * Vérifie la cohérence entre les heures d'ouverture/fermeture et le statut fermé.
     */
    @PrePersist
    @PreUpdate
    private void validateTimes() {
        if (closed) {
            return;
        }
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("startTime et endTime sont requis quand closed = false");
        }
        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("endTime doit être strictement après startTime");
        }
    }
}
