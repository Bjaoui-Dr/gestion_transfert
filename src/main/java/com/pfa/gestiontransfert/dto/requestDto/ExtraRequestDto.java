package com.pfa.gestiontransfert.dto.requestDto;

import com.pfa.gestiontransfert.enumerations.TypeExtra;
import lombok.Data;

@Data
public class ExtraRequestDto {
    private String nom;
    private TypeExtra type;
    private double tarif;
}
