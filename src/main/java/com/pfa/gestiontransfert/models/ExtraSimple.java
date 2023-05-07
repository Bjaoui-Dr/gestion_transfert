package com.pfa.gestiontransfert.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("simple")
public class ExtraSimple extends Extra {
    private double tarif;

    public ExtraSimple(String nom, double tarif) {
        super(nom);
        this.tarif = tarif;
    }
}
