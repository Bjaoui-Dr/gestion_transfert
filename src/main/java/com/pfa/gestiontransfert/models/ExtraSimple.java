package com.pfa.gestiontransfert.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ExtraSimple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExtraSimple;
    private String nom;
    private double tarif;

    public ExtraSimple(String nom, double tarif) {
        this.nom = nom;
        this.tarif = tarif;
    }
}
