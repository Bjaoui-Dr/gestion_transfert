package com.pfa.gestiontransfert.dto.requestDto;

import lombok.Data;

@Data
public class TrajetRequestDto {
    private  Long lieuDepartId;
    private  Long lieuArriverId;
    private boolean active;
}
