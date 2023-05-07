package com.pfa.gestiontransfert.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorColumn(name="extra_type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExtra;
    private String nom;

    public Extra(String nom){
        this.nom = nom;
    }
}
