package com.pfa.gestiontransfert.dto.requestDto;

import lombok.Data;

@Data
public class PrixRequestDto {
    private double prix;
    private Long modeleId;
    private Long trajetId;
    private Long periodeId;
}
