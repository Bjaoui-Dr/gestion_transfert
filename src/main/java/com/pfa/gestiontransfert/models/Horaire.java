package com.pfa.gestiontransfert.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
public class Horaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoraire;
    private LocalTime startTime;
    private LocalTime endTime;
    private double extraFees;

    // static field to hold the loaded Horaire object
    private static Horaire agenceHoraire;

    // constructor and other methods...

    @PostLoad
    private void postLoad() {
        agenceHoraire = this;
    }

    public static Horaire getAgenceHoraire() {
        return agenceHoraire;
    }

    public boolean isWithin(LocalTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
