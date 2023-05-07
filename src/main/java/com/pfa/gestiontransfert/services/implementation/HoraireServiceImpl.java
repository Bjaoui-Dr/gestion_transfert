package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.HoraireRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Horaire;
import com.pfa.gestiontransfert.repositories.HoraireRepository;
import com.pfa.gestiontransfert.services.HoraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HoraireServiceImpl implements HoraireService {
    private final HoraireRepository horaireRepository;

    @Autowired
    public HoraireServiceImpl(HoraireRepository horaireRepository){
        this.horaireRepository = horaireRepository;
    }

    @Override
    public Horaire getHoraire() throws BaseException {
        return horaireRepository.findById(1L)
                .orElseThrow(() -> new BaseException("Horaire not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public Horaire editHoraire(HoraireRequestDto horaireRequestDto) throws BaseException {
        Horaire horaireToEdit = getHoraire();
        horaireToEdit.setStartTime(horaireRequestDto.getStartTime());
        horaireToEdit.setEndTime(horaireRequestDto.getEndTime());
        return horaireToEdit;
    }
}
