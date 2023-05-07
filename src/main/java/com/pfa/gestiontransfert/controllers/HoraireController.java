package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.HoraireRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Horaire;
import com.pfa.gestiontransfert.services.HoraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/horaire")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HoraireController {
    private final HoraireService horaireService;

    @Autowired
    public HoraireController(HoraireService horaireService){
        this.horaireService = horaireService;
    }

    @GetMapping("/get")
    public ResponseEntity<Horaire> getHoraire() throws BaseException {
        Horaire horaire = horaireService.getHoraire();
        return new ResponseEntity<>(horaire, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Horaire> editHoraire(@RequestBody HoraireRequestDto horaireRequestDto) throws BaseException {
        Horaire horaireToEdit = horaireService.editHoraire(horaireRequestDto);
        return new ResponseEntity<>(horaireToEdit, HttpStatus.OK);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleHoraireException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
