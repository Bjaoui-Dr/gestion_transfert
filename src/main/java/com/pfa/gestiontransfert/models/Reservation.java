package com.pfa.gestiontransfert.models;

import com.pfa.gestiontransfert.enumerations.Etat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private LocalDateTime dateCreation;
    @ManyToOne
    private Trajet trajetDepart;
    private LocalDateTime dateAndTimeAller;
    @ManyToOne
    private Trajet trajetRetour;
    private LocalDateTime dateAndTimeRetour;
    private int nbrVoyageurs;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne
    private Modele model;
    private  int qteModele;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_extra",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id")
    )
    private List<Extra> extra = new ArrayList<>();
    private double totale; //totale (extraFees inclus s'il existe)  totale = totale + extraFees


// constructeur
    public Reservation(Trajet trajetDepart,
                       LocalDateTime dateAndTimeAller,
                       Trajet trajetRetour,
                       LocalDateTime dateAndTimeRetour,
                       int nbrVoyageurs,
                       Modele model,
                       int qteModele,
                       List<Extra> extra,
                       double totale) {
        this.dateCreation = LocalDateTime.now();
        this.etat = Etat.EN_ATTENDE;
        this.trajetDepart = trajetDepart;
        this.dateAndTimeAller = dateAndTimeAller;
        this.trajetRetour = trajetRetour;
        this.dateAndTimeAller =dateAndTimeRetour;
        this.nbrVoyageurs = nbrVoyageurs;
        this.model = model;
        this.qteModele = qteModele;
        this.extra = extra;
        this.totale = totale;
    }

    public void addExtra(Extra extra){
        this.extra.add(extra);
    }

    public void removeExtraSimple(Extra extra) {
        this.extra.remove(extra);
    }

    public void addFees(double extraFees) {
        this.totale += extraFees;
    }

}
