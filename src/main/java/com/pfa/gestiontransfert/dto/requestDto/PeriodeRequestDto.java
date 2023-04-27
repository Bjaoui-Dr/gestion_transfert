package com.pfa.gestiontransfert.dto.requestDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PeriodeRequestDto {
    private LocalDate dateDebut;
    private LocalDate dateFin;
}
