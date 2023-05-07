package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSimpleRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSimple;
import com.pfa.gestiontransfert.repositories.ExtraSimpleRepository;
import com.pfa.gestiontransfert.services.ExtraSimpleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@AllArgsConstructor
public class ExtraSimpleServiceImpl implements ExtraSimpleService {
    private final ExtraSimpleRepository extraSimpleRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExtraSimpleServiceImpl(ExtraSimpleRepository extraSimpleRepository,
                                  ModelMapper mapper){
        this.extraSimpleRepository = extraSimpleRepository;
        this.mapper = mapper;
    }


    @Override
    public ExtraSimple addExtraSimple(ExtraSimpleRequestDto extraSimpleRequestDto) {
        ExtraSimple extraSimple = mapper.map(extraSimpleRequestDto, ExtraSimple.class);
        return extraSimpleRepository.save(extraSimple);
    }

    @Override
    public List<ExtraSimple> getExtraSimples() {
        return extraSimpleRepository.findAll();
    }

    @Override
    public ExtraSimple getExtraSimpleById(Long extraSimpleId) throws BaseException {
        return extraSimpleRepository.findById(extraSimpleId)
                .orElseThrow(() -> new BaseException("Extra Simple not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ExtraSimple editExtraSimple(Long extraSimpleId, ExtraSimpleRequestDto extraSimpleRequestDto) throws BaseException {
        if(extraSimpleRepository.existsById(extraSimpleId)) {
            ExtraSimple extraSimpleToEdit = mapper.map(extraSimpleRequestDto, ExtraSimple.class);
            extraSimpleToEdit.setIdExtra(extraSimpleId);
            return extraSimpleRepository.save(extraSimpleToEdit);

        } else {
            throw new BaseException("Extra Simple not found", HttpStatus.NOT_FOUND);
        }
    }
}
