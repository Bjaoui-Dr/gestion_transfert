package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.TrajetRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;
import com.pfa.gestiontransfert.services.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/trajet")
public class TrajetController {
    public final TrajetService trajetService;

    @Autowired
    public TrajetController(TrajetService trajetService){
        this.trajetService = trajetService;
    }

    @PostMapping("/add")
    public ResponseEntity<Trajet> addTrajet(@RequestBody TrajetRequestDto trajetRequestDto) throws BaseException {
        Trajet trajet = trajetService.addTrajet(trajetRequestDto);
        return new ResponseEntity<>(trajet, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trajet>> getTrajets(){
        List<Trajet> trajets = trajetService.getTrajets();
        return new ResponseEntity<>(trajets, HttpStatus.OK);
    }

    @GetMapping("{trajetId}")
    public ResponseEntity<Trajet> getTrajetById(@PathVariable Long trajetId) throws BaseException {
        Trajet trajet = trajetService.getTrajetById(trajetId);
        return new ResponseEntity<>(trajet, HttpStatus.OK);
    }

    // Trouver tout les lieux d'arriver quand le lieu de depart est selectionner et ce trajet doit etre actif
    // idDepart "C'est du lieu"
    @GetMapping("/lieuArriver/{LieuDepart}")
    public ResponseEntity<List<Lieu>> findLieuDepartByLieuArriverTrajetActive(@PathVariable Long LieuDepart) throws BaseException {
        List<Lieu> lieuxArriver = trajetService.findLieuDepartByLieuArriverTrajetActive(LieuDepart);
        return new ResponseEntity<>(lieuxArriver, HttpStatus.OK);
    }

    // Trouver tout les lieux de depart quand le trajet est active
    @GetMapping("/lieuxDepart")
    public ResponseEntity<List<Lieu>> findDistinctLieuDepartByActiveIsTrue() {
        List<Lieu> lieuxDepart = trajetService.findDistinctLieuDepartByActiveIsTrue();
        return new ResponseEntity<>(lieuxDepart, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trajet> editTrajet(@PathVariable Long id, @RequestBody TrajetRequestDto trajetRequestDto)
            throws BaseException {
        Trajet trajet = trajetService.editTrajet(id, trajetRequestDto);
        return new ResponseEntity<>(trajet,HttpStatus.OK);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleTrajetException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
