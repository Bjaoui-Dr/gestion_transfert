package com.pfa.gestiontransfert.services.implementation;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
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
    public Modele addModele(String nom,
                            int nbrPlace,
                            TypeModel typeVoiture,
                            boolean active,
                            MultipartFile file) throws BaseException {
        Modele modele = new Modele();
//        if (modele.getTypeVoiture() == null ||
//                !Arrays.asList(TypeModel.values()).contains(modele.getTypeVoiture())) {
//            throw new BaseException("Invalid typeVoiture", HttpStatus.BAD_REQUEST);
//        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            modele.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        modele.setNom(nom);
        modele.setActive(active);
        modele.setNbrPlace(nbrPlace);
        modele.setTypeVoiture(typeVoiture);
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
