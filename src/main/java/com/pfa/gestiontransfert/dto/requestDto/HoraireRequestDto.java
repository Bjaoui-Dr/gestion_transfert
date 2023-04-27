package com.pfa.gestiontransfert.dto.requestDto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class HoraireRequestDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private double extraFees;
}
