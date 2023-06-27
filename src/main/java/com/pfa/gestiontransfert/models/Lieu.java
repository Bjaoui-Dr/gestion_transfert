package com.pfa.gestiontransfert.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLieu;
    @Column(nullable = false)
    String nomLieu;
    Double longitude;
    Double latitude;

    public Lieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
}
