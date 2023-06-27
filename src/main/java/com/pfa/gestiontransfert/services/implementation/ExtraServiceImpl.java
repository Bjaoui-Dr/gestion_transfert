package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.ExtraRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Extra;
import com.pfa.gestiontransfert.repositories.ExtraRepository;
import com.pfa.gestiontransfert.services.ExtraService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraServiceImpl implements ExtraService {
    private final ExtraRepository extraRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExtraServiceImpl(ExtraRepository extraRepository, ModelMapper mapper) {
        this.extraRepository = extraRepository;
        this.mapper = mapper;
    }

    @Override
    public Extra addExtra(ExtraRequestDto extraRequestDto) {
        Extra extra = mapper.map(extraRequestDto, Extra.class);
        return extraRepository.save(extra);
    }

    @Override
    public List<Extra> getExtras() {
        return extraRepository.findAll();
    }

    @Override
    public Extra getExtraById(Long extralId) throws BaseException {
        return extraRepository.findById(extralId)
                .orElseThrow(() -> new BaseException("Extra not found", HttpStatus.NOT_FOUND) );
    }

    @Override
    public Extra editExtra(Long extraId, ExtraRequestDto extraRequestDto) throws BaseException {
        if(extraRepository.existsById(extraId)) {
            Extra extraToEdit = mapper.map(extraRequestDto, Extra.class);
            extraToEdit.setIdExtra(extraId);
            return extraRepository.save(extraToEdit);
        } else {
            throw new BaseException("Extra Simple not found", HttpStatus.NOT_FOUND);
        }

    }
}
