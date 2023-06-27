package com.pfa.gestiontransfert.models;

import com.pfa.gestiontransfert.enumerations.TypeExtra;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExtra;
    private String nom;
    @Enumerated(EnumType.STRING)
    private TypeExtra type;
    private double tarif;


    public Extra(String nom){
        this.nom = nom;
    }
}
