package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.LieuException;
import com.pfa.gestiontransfert.exceptions.exceptionHandling.ResponseException;
import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.services.LieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/lieu")
public class LieuController {
    public final LieuService lieuService;

    @Autowired
    public LieuController(LieuService lieuService) {
        this.lieuService = lieuService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lieu>> getLieux(){
        List<Lieu> lieux = lieuService.getLieux();
        return new ResponseEntity<>(lieux, HttpStatus.OK);
    }

    @GetMapping("/{lieuId}")
    public ResponseEntity<Lieu> getLieuById(@PathVariable Long lieuId) throws LieuException {
        Lieu lieu = lieuService.getLieuById(lieuId);
        return new ResponseEntity<>(lieu,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Lieu> addLieu(@RequestBody LieuRequestDto lieuRequestDto) {
        Lieu lieu = lieuService.addLieu(lieuRequestDto);
        return new ResponseEntity<>(lieu, HttpStatus.CREATED);
    }

    @PutMapping("/{lieuId}")
    public ResponseEntity<Lieu> editLieu(@PathVariable Long lieuId, @RequestBody LieuRequestDto lieuRequestDto) throws LieuException {
        Lieu lieu = lieuService.editLieu(lieuId, lieuRequestDto);
        return new ResponseEntity<>(lieu, HttpStatus.OK);
    }

    @ExceptionHandler(value = LieuException.class)
    public ResponseEntity<Object> handleLieuException(LieuException e) {
        ResponseException responseException = new ResponseException(e.getHttpStatus().value(), e.getMessage(),
                e.getHttpStatus(), ZonedDateTime.now());
        return new ResponseEntity<>(responseException, e.getHttpStatus());
    }

}