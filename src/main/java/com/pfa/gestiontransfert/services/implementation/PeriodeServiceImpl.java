package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.PeriodeRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Periode;
import com.pfa.gestiontransfert.repositories.PeriodeRepository;
import com.pfa.gestiontransfert.services.PeriodeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodeServiceImpl implements PeriodeService {

    private final ModelMapper mapper;
    private final PeriodeRepository periodeRepository;

    @Autowired
    public PeriodeServiceImpl(ModelMapper mapper, PeriodeRepository periodeRepository){
        this.mapper = mapper;
        this.periodeRepository = periodeRepository;
    }

    @Override
    public Periode addPeriode(PeriodeRequestDto periodeRequestDto) {
        Periode periode = mapper.map(periodeRequestDto, Periode.class);
        return periodeRepository.save(periode);
    }

    @Override
    public Periode getPeriodeById(Long periodeId) throws BaseException {
        return periodeRepository.findById(periodeId)
                .orElseThrow(()-> new BaseException("Periode not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Periode> getAllPeriodes() {
        return periodeRepository.findAll();
    }

    @Override
    public Periode editePeriode(Long periodeId, PeriodeRequestDto periodeRequestDto) throws BaseException {
        if (periodeRepository.existsById(periodeId)){
            Periode periode = mapper.map(periodeRequestDto, Periode.class);
            periode.setIdPeriode(periodeId);
            return periodeRepository.save(periode);
        } else {
            throw new BaseException("Periode not found", HttpStatus.NOT_FOUND);
        }
    }
}
