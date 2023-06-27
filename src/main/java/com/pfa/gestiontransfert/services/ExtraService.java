package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.ExtraRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Extra;

import java.util.List;


public interface ExtraService {
    public Extra addExtra(ExtraRequestDto extraRequestDto);
    public List<Extra> getExtras();
    public Extra getExtraById(Long extralId) throws BaseException;
    public Extra editExtra(Long extraId, ExtraRequestDto extraRequestDto) throws BaseException;
}
