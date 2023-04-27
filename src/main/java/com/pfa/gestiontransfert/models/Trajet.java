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

    public Trajet(Lieu lieuDepart, Lieu lieuArriver, boolean active) {
        this.lieuDepart = lieuDepart;
        this.lieuArriver = lieuArriver;
        this.active = active;
    }
}
