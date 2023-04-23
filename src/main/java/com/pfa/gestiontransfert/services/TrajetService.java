package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.TrajetRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;

import java.util.List;

public interface TrajetService {
    public Trajet addTrajet(TrajetRequestDto trajetRequestDto) throws BaseException;
    public List<Trajet> getTrajets();
    public Trajet getTrajetById(Long id) throws BaseException;
    public List<Lieu> findLieuDepartByLieuArriverTrajetActive(Long lieuDepartId) throws BaseException;
    public List<Lieu> findDistinctLieuDepartByActiveIsTrue();
    public Trajet editTrajet(Long id, TrajetRequestDto trajetRequestDto) throws BaseException;
}
