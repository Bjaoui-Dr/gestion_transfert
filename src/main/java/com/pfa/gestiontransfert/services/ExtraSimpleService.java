package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSimpleRequestDto;
import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSimple;
import com.pfa.gestiontransfert.models.Lieu;

import java.util.List;

public interface ExtraSimpleService {
    public ExtraSimple addExtraSimple(ExtraSimpleRequestDto extraSimpleRequestDto);
    public List<ExtraSimple> getExtraSimples();
    public ExtraSimple getExtraSimpleById(Long extraSimpleId) throws BaseException;
    public ExtraSimple editExtraSimple(Long extraSimpleId, ExtraSimpleRequestDto extraSimpleRequestDto) throws BaseException;
}
