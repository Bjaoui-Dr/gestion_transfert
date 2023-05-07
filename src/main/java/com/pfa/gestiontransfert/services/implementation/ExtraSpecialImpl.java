package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSpecialRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSpecial;
import com.pfa.gestiontransfert.repositories.ExtraSpecialRepository;
import com.pfa.gestiontransfert.services.ExtraSpecialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraSpecialImpl implements ExtraSpecialService {

    private final ExtraSpecialRepository extraSpecialRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExtraSpecialImpl(ExtraSpecialRepository extraSpecialRepository,
                            ModelMapper mapper) {
        this.extraSpecialRepository = extraSpecialRepository;
        this.mapper = mapper;
    }
    @Override
    public ExtraSpecial addExtraSpecial(ExtraSpecialRequestDto extraSpecialRequestDto) {
        ExtraSpecial extraSpecial = mapper.map(extraSpecialRequestDto, ExtraSpecial.class);
        return extraSpecialRepository.save(extraSpecial);
    }

    @Override
    public List<ExtraSpecial> getExtraSpecial() {
        return extraSpecialRepository.findAll();
    }

    @Override
    public ExtraSpecial getExtraSpecialById(Long extraSpecialId) throws BaseException {
        return extraSpecialRepository.findById(extraSpecialId)
                .orElseThrow(()-> new BaseException("Extra Special not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ExtraSpecial editExtraSpecial(Long extraSpecialId, ExtraSpecialRequestDto extraSpecialRequestDto) throws BaseException {
        if(extraSpecialRepository.existsById(extraSpecialId)) {
            ExtraSpecial extraSpecialToEdit = mapper.map(extraSpecialRequestDto, ExtraSpecial.class);
            extraSpecialToEdit.setIdExtra(extraSpecialId);
            return extraSpecialRepository.save(extraSpecialToEdit);
        }
        else {
            throw new BaseException("Extra special not found", HttpStatus.NOT_FOUND);
        }
    }
}
