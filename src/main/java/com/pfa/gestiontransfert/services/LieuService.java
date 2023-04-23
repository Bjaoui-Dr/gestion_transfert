package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.LieuException;
import com.pfa.gestiontransfert.models.Lieu;

import java.util.List;


public interface LieuService {
    public Lieu addLieu(LieuRequestDto lieuRequestDto);
    public List<Lieu> getLieux();
    public Lieu getLieuById(Long lieuId) throws LieuException;
    public Lieu editLieu(Long lieuId, LieuRequestDto lieuRequestDto) throws LieuException;
}
