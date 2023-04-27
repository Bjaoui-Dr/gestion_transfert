package com.pfa.gestiontransfert.dto.requestDto;

import com.pfa.gestiontransfert.enumerations.TypeModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ModelRequestDto {
    private String nom;
    private int nbrPlace;
    private TypeModel typeVoiture;
}
