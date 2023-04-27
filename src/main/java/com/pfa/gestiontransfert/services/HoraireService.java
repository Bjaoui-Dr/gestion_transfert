package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.HoraireRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Horaire;
import com.pfa.gestiontransfert.models.Lieu;

public interface HoraireService {
    public Horaire getHoraire() throws BaseException;
    public Horaire editHoraire(HoraireRequestDto horaireRequestDto) throws BaseException;
}
