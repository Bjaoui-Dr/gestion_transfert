package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.TrajetRequestDto;
import com.pfa.gestiontransfert.exceptions.LieuException;
import com.pfa.gestiontransfert.exceptions.TrajetException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;

import java.util.List;

public interface TrajetService {
    public Trajet addTrajet(TrajetRequestDto trajetRequestDto) throws TrajetException, LieuException;
    public List<Trajet> getTrajets();
    public Trajet getTrajetById(Long id) throws TrajetException;
    public List<Lieu> findLieuDepartByLieuArriverTrajetActive(Long lieuDepartId) throws LieuException;
    public List<Lieu> findDistinctLieuDepartByActiveIsTrue();
    public Trajet editTrajet(Long id, TrajetRequestDto trajetRequestDto) throws TrajetException, LieuException;
}
