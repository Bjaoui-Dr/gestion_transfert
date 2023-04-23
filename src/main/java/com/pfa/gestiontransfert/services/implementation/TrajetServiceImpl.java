package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.TrajetRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;
import com.pfa.gestiontransfert.repositories.TrajetRepository;
import com.pfa.gestiontransfert.services.LieuService;
import com.pfa.gestiontransfert.services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrajetServiceImpl implements TrajetService {

    private final TrajetRepository trajetRepository;
    private final LieuService lieuService;

    @Autowired
    public TrajetServiceImpl(TrajetRepository trajetRepository, LieuService lieuService){
        this.trajetRepository = trajetRepository;
        this.lieuService = lieuService;
    }

    @Transactional
    @Override
    public Trajet addTrajet(TrajetRequestDto trajetRequestDto) throws BaseException {
        Trajet trajet = new Trajet();
        if(trajetRequestDto.getLieuDepartId() == null ||
                trajetRequestDto.getLieuArriverId() == null) {
            throw new BaseException("Lieu depart et arriver ne peuvent pas etre null",
                    HttpStatus.BAD_REQUEST);
        }
        if(trajetRequestDto.getLieuDepartId() == trajetRequestDto.getLieuArriverId()){
            throw new BaseException("Le lieu depart ne peut pas etre le lieu d'arriver",HttpStatus.BAD_REQUEST);
        }
        Lieu lieuDepart = lieuService.getLieuById(trajetRequestDto.getLieuDepartId());
        Lieu lieuArriver = lieuService.getLieuById(trajetRequestDto.getLieuArriverId());
        trajet.setLieuDepart(lieuDepart);
        trajet.setLieuArriver(lieuArriver);
        trajet.setActive(trajetRequestDto.isActive());
        return trajetRepository.save(trajet);
    }

    @Override
    public List<Trajet> getTrajets() {
        return trajetRepository.findAll();
    }

    @Override
    public Trajet getTrajetById(Long id) throws BaseException {
        return trajetRepository.findById(id)
                .orElseThrow(() -> new BaseException("trajet not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Lieu> findLieuDepartByLieuArriverTrajetActive(Long LieuDepart) throws BaseException {
        return trajetRepository.findLieuDepartByLieuArriverTrajetActive(LieuDepart);
    }


    @Override
    public List<Lieu> findDistinctLieuDepartByActiveIsTrue() {
        return trajetRepository.findDistinctLieuDepartByActiveIsTrue();
    }

    @Transactional
    @Override
    public Trajet editTrajet(Long id, TrajetRequestDto trajetRequestDto) throws BaseException {
        Trajet trajetToEdit = getTrajetById(id);
        if((trajetRequestDto.getLieuDepartId() != trajetRequestDto.getLieuArriverId())){
            Lieu lieuDepart = lieuService.getLieuById(trajetRequestDto.getLieuDepartId());
            Lieu lieuArriver = lieuService.getLieuById(trajetRequestDto.getLieuArriverId());
            trajetToEdit.setLieuDepart(lieuDepart);
            trajetToEdit.setLieuArriver(lieuArriver);
            trajetToEdit.setActive(trajetRequestDto.isActive());
            return trajetRepository.save(trajetToEdit);
        } else {
            throw new BaseException("verifier le lieu de depart et arriver avant modification du trajet", HttpStatus.BAD_REQUEST);
        }
    }
}
