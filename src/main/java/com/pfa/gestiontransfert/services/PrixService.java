package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.PrixRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Prix;

import java.util.List;

public interface PrixService {
    public Prix addPrix(PrixRequestDto prixRequestDto) throws BaseException;
    public List<Prix> getAllPrix();
    public double getPrixByModelTrajetPeriode(Long idModele, Long idTrajet, Long idPeriode) throws BaseException;
    public List<Prix> getPrixByTrajet(Long trajetId) throws BaseException;
    public Prix getPrixById(Long idPrix) throws BaseException;
    public Prix editPrix(Long prixId, PrixRequestDto prixRequestDto) throws BaseException;
}
