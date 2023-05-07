package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.LieuRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LieuController {
    private final LieuService lieuService;

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
    public ResponseEntity<Lieu> getLieuById(@PathVariable Long lieuId) throws BaseException {
        Lieu lieu = lieuService.getLieuById(lieuId);
        return new ResponseEntity<>(lieu,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Lieu> addLieu(@RequestBody LieuRequestDto lieuRequestDto) {
        Lieu lieu = lieuService.addLieu(lieuRequestDto);
        return new ResponseEntity<>(lieu, HttpStatus.CREATED);
    }

    @PutMapping("/{lieuId}")
    public ResponseEntity<Lieu> editLieu(@PathVariable Long lieuId, @RequestBody LieuRequestDto lieuRequestDto) throws BaseException {
        Lieu lieu = lieuService.editLieu(lieuId, lieuRequestDto);
        return new ResponseEntity<>(lieu, HttpStatus.OK);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

}
