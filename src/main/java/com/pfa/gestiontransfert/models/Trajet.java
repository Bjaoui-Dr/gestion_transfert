package com.pfa.gestiontransfert.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrajet;
    @ManyToOne
    private  Lieu lieuDepart;
    @ManyToOne
    private  Lieu lieuArriver;
    @Column(nullable = false)
    private boolean active;
    private double price;
    private float distance;

    public Trajet(Lieu lieuDepart, Lieu lieuArriver, boolean active, double price, float distance) {
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.active = active;
        this.price = price;
        this.distance = distance;
    }
}
