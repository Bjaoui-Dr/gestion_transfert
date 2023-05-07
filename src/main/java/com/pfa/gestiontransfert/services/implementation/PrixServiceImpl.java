package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.PrixRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Modele;
import com.pfa.gestiontransfert.models.Periode;
import com.pfa.gestiontransfert.models.Prix;
import com.pfa.gestiontransfert.models.Trajet;
import com.pfa.gestiontransfert.repositories.PrixRepository;
import com.pfa.gestiontransfert.services.ModeleService;
import com.pfa.gestiontransfert.services.PeriodeService;
import com.pfa.gestiontransfert.services.PrixService;
import com.pfa.gestiontransfert.services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrixServiceImpl implements PrixService {
    private final ModeleService modeleService;
    private final TrajetService trajetService;
    private final PeriodeService periodeService;
    private final PrixRepository prixRepository;

    @Autowired
    PrixServiceImpl(ModeleService modeleService,
                    TrajetService trajetService,
                    PeriodeService periodeService,
                    PrixRepository prixRepository){
        this.modeleService = modeleService;
        this.trajetService = trajetService;
        this.periodeService = periodeService;
        this.prixRepository = prixRepository;
    }

    @Transactional
    @Override
    public Prix addPrix(PrixRequestDto prixRequestDto) throws BaseException {
        if(prixRequestDto.getTrajetId() == null) {
            throw new BaseException("trajet ne doit pas etre null", HttpStatus.BAD_REQUEST);
        }
        if(prixRequestDto.getModeleId() == null) {
            throw new BaseException("modele ne doit pas etre null", HttpStatus.BAD_REQUEST);
        }
        if(prixRequestDto.getPeriodeId() == null) {
            throw new BaseException("periode ne doit pas etre null", HttpStatus.BAD_REQUEST);
        }
        Prix prix = new Prix();
        Modele modele = modeleService.getModeleById(prixRequestDto.getModeleId());
        Trajet trajet = trajetService.getTrajetById(prixRequestDto.getTrajetId());
        Periode periode = periodeService.getPeriodeById(prixRequestDto.getPeriodeId());
        prix.setModele(modele);
        prix.setTrajet(trajet);
        prix.setPeriode(periode);
        prix.setPrix(prixRequestDto.getPrix());
        return prixRepository.save(prix);
    }

    @Override
    public List<Prix> getAllPrix() {
        return prixRepository.findAll();
    }

    @Override
    public Prix getPrixByModelTrajetPeriode(Long prixId, Long modelId, Long trajetId) throws BaseException {
        return null;
    }

    @Override
    public List<Prix> getPrixByTrajet(Long trajetId) throws BaseException {
        return null;
    }

    @Override
    public Prix getPrixById(Long idPrix) throws BaseException {
        return prixRepository.findById(idPrix)
                .orElseThrow(()-> new BaseException("Prix not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public Prix editPrix(Long prixId, PrixRequestDto prixRequestDto) throws BaseException {
        Prix prixToEdit = getPrixById(prixId);
        Modele modele = modeleService.getModeleById(prixRequestDto.getModeleId());
        Trajet trajet = trajetService.getTrajetById(prixRequestDto.getTrajetId());
        Periode periode = periodeService.getPeriodeById(prixToEdit.getIdPrix());
        prixToEdit.setModele(modele);
        prixToEdit.setTrajet(trajet);
        prixToEdit.setPeriode(periode);
        prixToEdit.setPrix(prixRequestDto.getPrix());
        return prixToEdit;
    }
}
