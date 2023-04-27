package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.repositories.LieuRepository;
import com.pfa.gestiontransfert.services.LieuService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LieuServiceImpl implements LieuService {

    private final LieuRepository lieuRepository;
    private final ModelMapper mapper;

    @Autowired
    public LieuServiceImpl(LieuRepository lieuRepository,
                           ModelMapper mapper) {
        this.lieuRepository = lieuRepository;
        this.mapper = mapper;
    }

    @Override
    public Lieu addLieu(LieuRequestDto lieuRequestDto) {
        Lieu lieu = mapper.map(lieuRequestDto, Lieu.class);
        return lieuRepository.save(lieu);
    }

    @Override
    public List<Lieu> getLieux() {
        return lieuRepository.findAll();
    }

    @Override
    public Lieu getLieuById(Long lieuId) throws BaseException {
        return lieuRepository.findById(lieuId)
                .orElseThrow(() -> new BaseException("lieu not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public Lieu editLieu(Long lieuId, LieuRequestDto lieuRequestDto) throws BaseException {
        Lieu lieuToEdit = getLieuById(lieuId);
        lieuToEdit.setNomLieu(lieuRequestDto.getNomLieu());
        return lieuToEdit;
    }
}
