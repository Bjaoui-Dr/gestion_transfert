package com.pfa.gestiontransfert.models;

import com.pfa.gestiontransfert.enumerations.TypeVoiture;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private int nbr_place;
    private double tarif;
    private TypeVoiture type_voiture;

}
