package com.pfa.gestiontransfert.models;

import com.pfa.gestiontransfert.enumerations.TypeModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Data
@NoArgsConstructor
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModel;
    private String nom;
    private int nbrPlace;
    @Enumerated(EnumType.STRING)
    private TypeModel typeVoiture;
    private boolean active;
    private double price;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    public Modele(String nom, int nbrPlace, TypeModel typeVoiture, String imgPath) {
        this.nom = nom;
        this.nbrPlace = nbrPlace;
        this.typeVoiture = typeVoiture;
    }

}
