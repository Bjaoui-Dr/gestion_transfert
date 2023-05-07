package com.pfa.gestiontransfert.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Periode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeriode;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Periode(LocalDate dateDebut, LocalDate dateFin){
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
