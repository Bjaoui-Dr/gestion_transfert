package com.pfa.gestiontransfert.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Prix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrix;
    private double prix;
    @ManyToOne
    private Modele modele;
    @ManyToOne
    private Trajet trajet;
    @ManyToOne
    private Periode periode;
}
