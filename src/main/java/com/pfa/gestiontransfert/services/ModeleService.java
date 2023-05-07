package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.ModelRequestDto;
import com.pfa.gestiontransfert.enumerations.TypeModel;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Modele;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ModeleService {
    public Modele addModele(String nom,
                            int nbrPlace,
                            TypeModel typeVoiture,
                            boolean active,
                            MultipartFile file) throws BaseException;
    public List<Modele> getAllModeles();
    public Modele getModeleById(Long modelId) throws BaseException;
    public Modele editModele(Long modelId, ModelRequestDto modelRequestDto) throws  BaseException;
}
