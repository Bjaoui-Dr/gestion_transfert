package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Lieu;

import java.util.List;


public interface LieuService {
    public Lieu addLieu(LieuRequestDto lieuRequestDto);
    public List<Lieu> getLieux();
    public Lieu getLieuById(Long lieuId) throws BaseException;
    public Lieu editLieu(Long lieuId, LieuRequestDto lieuRequestDto) throws BaseException;
}
