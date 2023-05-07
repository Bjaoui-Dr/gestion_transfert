package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.PrixRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Prix;
import com.pfa.gestiontransfert.services.PrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prix")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PrixController {
    private final PrixService prixService;

    public PrixController(PrixService prixService) {
        this.prixService = prixService;
    }

    @PostMapping("/add")
    public ResponseEntity<Prix> addPrix(@RequestBody PrixRequestDto prixRequestDto) throws BaseException {
        Prix prix = prixService.addPrix(prixRequestDto);
        return new ResponseEntity<>(prix, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prix>> getAllPrix() {
        List<Prix> prix = prixService.getAllPrix();
        return new ResponseEntity<>(prix, HttpStatus.OK);
    }

    @GetMapping("/{idPrix}")
    public ResponseEntity<Prix> getPrixById(@PathVariable Long idPrix) throws BaseException {
        Prix prix = prixService.getPrixById(idPrix);
        return new ResponseEntity<>(prix, HttpStatus.OK);
    }

    @PutMapping("/{prixId}")
    public ResponseEntity<Prix> editPrix(@PathVariable Long prixId, @RequestBody PrixRequestDto prixRequestDto) throws BaseException {
        Prix prix = prixService.editPrix(prixId, prixRequestDto);
        return new ResponseEntity<>(prix, HttpStatus.OK);
    }


    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
