package com.pfa.gestiontransfert.dto.requestDto;

import com.pfa.gestiontransfert.enumerations.Etat;
import com.pfa.gestiontransfert.models.Extra;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequestDto {
    Long idTrajetDepart;
    LocalDateTime dateAndTimeAller;
    Long idTrajetRetour;
    LocalDateTime dateAndTimeRetour;
    int nbrVoyageurs;
    Long idModel;
    int qteModele;
    List<Long> idExtras;
}
