package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.ModelRequestDto;
import com.pfa.gestiontransfert.enumerations.TypeModel;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Modele;
import com.pfa.gestiontransfert.repositories.ModelRepository;
import com.pfa.gestiontransfert.services.ModeleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ModelServiceImpl implements ModeleService {
    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelMapper mapper, ModelRepository modelRepository){
        this.modelRepository = modelRepository;
        this.mapper = mapper;

    }

    @Override
    public Modele addModele(ModelRequestDto modelRequestDto) throws BaseException {
        Modele modele = mapper.map(modelRequestDto, Modele.class);
        if (modele.getTypeVoiture() == null ||
                !Arrays.asList(TypeModel.values()).contains(modele.getTypeVoiture())) {
            throw new BaseException("Invalid typeVoiture", HttpStatus.BAD_REQUEST);
        }
        return modelRepository.save(modele);
    }

    @Override
    public List<Modele> getAllModeles() {
        return modelRepository.findAll();
    }

    @Override
    public Modele getModeleById(Long modelId) throws BaseException {
        return modelRepository.findById(modelId)
                .orElseThrow(()-> new BaseException("Modele not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Modele editModele(Long modelId, ModelRequestDto modelRequestDto) throws BaseException {
        if (modelRepository.existsById(modelId)){
            Modele modelToEdit = mapper.map(modelRequestDto, Modele.class);
            modelToEdit.setIdModel(modelId);
            if (modelToEdit.getTypeVoiture() == null ||
                    !Arrays.asList(TypeModel.values()).contains(modelToEdit.getTypeVoiture())) {
                throw new BaseException("Invalid typeVoiture", HttpStatus.BAD_REQUEST);
            }
            return modelRepository.save(modelToEdit);
        } else {
            throw new BaseException("Modele not found", HttpStatus.NOT_FOUND);
        }
    }
}
