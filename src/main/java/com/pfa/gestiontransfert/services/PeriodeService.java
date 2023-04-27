package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.PeriodeRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Periode;

import java.util.List;

public interface PeriodeService {
    Periode addPeriode(PeriodeRequestDto periodeRequestDto);
    Periode getPeriodeById(Long periodeId) throws BaseException;
    List<Periode> getAllPeriodes();
    Periode editePeriode(Long periodeId, PeriodeRequestDto periodeRequestDto) throws BaseException;
}
