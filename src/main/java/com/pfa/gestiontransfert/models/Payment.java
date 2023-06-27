package com.pfa.gestiontransfert.models;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.io.IOException;
import java.util.UUID;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPayment;
    private double amount;
    private String status;
    private String method; // e.g. "PayPal"
    private String transactionId; // ID of the PayPal transaction
    @ManyToOne
    private Reservation reservation;

}

