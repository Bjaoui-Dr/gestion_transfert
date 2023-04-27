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
            name = "reservation_extra_simple",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "extra_simple_id")
    )
    private List<ExtraSimple> extraSimples = new ArrayList<>();
    private double extraFees;
    private double totale; //totale (extraFees inclus s'il existe)  totale = totale + extraFees


// constructeur
//    public Reservation() {
//        this.date_creation = LocalDateTime.now();
//        this.etat = Etat.EN_ATTENDE;
//    }

    public void addExtraSimple(ExtraSimple extraSimple){
        this.extraSimples.add(extraSimple);
    }

    public void removeExtraSimple(ExtraSimple extraSimple) {
        this.extraSimples.remove(extraSimple);
    }


//    public void calculateExtraFees(Reservation reservation, Horaire horaire) {
//        LocalDateTime dateAndTimeAller = reservation.getDateAndTimeAller();
//        LocalTime timeAller = dateAndTimeAller.toLocalTime();
//
//        // Compare the timeAller with the agency's opening and closing times
//        LocalTime startTime = horaire.getStartTime();
//        LocalTime endTime = horaire.getEndTime();
//
//        if (timeAller.isBefore(startTime) || timeAller.isAfter(endTime)) {
//            // Calculate the extra fees if the timeAller is outside of the agency's working hours
//            Double totalPrice = reservation.getTotale();
//            Double extraFees = totalPrice * horaire.getExtraFees(); // 10% extra fees
//            this.setExtraFees(extraFees);
//            reservation.setTotale(totalPrice + extraFees);
//        }
//    }
}
