package com.pfa.gestiontransfert.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("2")
public class Agent extends User {
    public  Agent(String nom,
                   String prenom,
                   String email,
                   String password) {
        super(nom, prenom, email, password);
    }
}
