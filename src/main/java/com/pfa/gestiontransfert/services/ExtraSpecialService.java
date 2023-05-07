package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSimpleRequestDto;
import com.pfa.gestiontransfert.dto.requestDto.ExtraSpecialRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSimple;
import com.pfa.gestiontransfert.models.ExtraSpecial;

import java.util.List;

public interface ExtraSpecialService {
    public ExtraSpecial addExtraSpecial(ExtraSpecialRequestDto extraSpecialRequestDto);
    public List<ExtraSpecial> getExtraSpecial();
    public ExtraSpecial getExtraSpecialById(Long extraSpecialId) throws BaseException;
    public ExtraSpecial editExtraSpecial(Long extraSpecialId, ExtraSpecialRequestDto extraSpecialRequestDto) throws BaseException;
}
