package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.LieuException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.repositories.LieuRepository;
import com.pfa.gestiontransfert.services.LieuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuServiceImpl implements LieuService {

    private final LieuRepository lieuRepository;

    @Autowired
    public LieuServiceImpl(LieuRepository lieuRepository) {
        this.lieuRepository = lieuRepository;
    }

    @Override
    public Lieu addLieu(LieuRequestDto lieuRequestDto) {
        Lieu lieu = new Lieu();
        lieu.setNomLieu(lieuRequestDto.getNomLieu());
        return lieuRepository.save(lieu);
    }

    @Override
    public List<Lieu> getLieux() {
        return lieuRepository.findAll();
    }

    @Override
    public Lieu getLieuById(Long lieuId) throws LieuException {
        return lieuRepository.findById(lieuId)
                .orElseThrow(() -> new LieuException("lieu not found"));
    }

    @Transactional
    @Override
    public Lieu editLieu(Long lieuId, LieuRequestDto lieuRequestDto) throws LieuException{
        Lieu lieuToEdit = getLieuById(lieuId);
        lieuToEdit.setNomLieu(lieuRequestDto.getNomLieu());
        return lieuToEdit;
    }
}
