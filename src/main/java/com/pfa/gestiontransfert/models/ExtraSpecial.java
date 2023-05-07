package com.pfa.gestiontransfert.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("special")
public class ExtraSpecial extends Extra {
    private double percent;

    public ExtraSpecial(String nom, double percent) {
        super(nom);
        this.percent = percent;
    }
}
