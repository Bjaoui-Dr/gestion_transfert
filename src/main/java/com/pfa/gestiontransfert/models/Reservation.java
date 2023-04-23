package com.pfa.gestiontransfert.models;

import com.pfa.gestiontransfert.enumerations.Etat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date_creation;
    private LocalTime heure_depart;
    private double totale;
    private double extra_payment;
    private String nbr_voyageurs;
    private Etat etat;
    private  int qte_modele;

    public Reservation(LocalTime heure_depart, double totale, String nbr_voyageurs, int qte_modele) {
        this.date_creation = new Date();
        this.heure_depart = heure_depart;
        this.totale = totale;
        this.nbr_voyageurs = nbr_voyageurs;
        this.etat = Etat.EN_ATTENDE;
        this.qte_modele = qte_modele;
    }
}
