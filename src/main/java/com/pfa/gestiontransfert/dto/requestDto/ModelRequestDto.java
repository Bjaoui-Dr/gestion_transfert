package com.pfa.gestiontransfert.dto.requestDto;

import com.pfa.gestiontransfert.enumerations.TypeModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.Data;


@Data
public class ModelRequestDto {
    private String nom;
    private int nbrPlace;
    private TypeModel typeVoiture;
    private boolean active;
    private double price;
}
