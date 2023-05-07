package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.ModelRequestDto;
import com.pfa.gestiontransfert.enumerations.TypeModel;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Horaire;
import com.pfa.gestiontransfert.models.Modele;
import com.pfa.gestiontransfert.services.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/modele")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ModeleController {
    private final ModeleService modeleService;

    @Autowired
    public ModeleController(ModeleService modeleService){
        this.modeleService = modeleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Modele>> getAllModels(){
        List<Modele> modeles = modeleService.getAllModeles();
        return new ResponseEntity<>(modeles, HttpStatus.OK);
    }

    @GetMapping("/{idModele}")
    public ResponseEntity<Modele> getModeleById(@PathVariable Long idModele) throws BaseException {
        Modele modele = modeleService.getModeleById(idModele);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Modele> addModele(@RequestParam("nom") String nom,
                                            @RequestParam("nbrPlace") int nbrPlace,
                                            @RequestParam("typeVoiture") TypeModel typeVoiture,
                                            @RequestParam("active") boolean active,
                                            @RequestParam("file") MultipartFile file) throws BaseException, IOException{
        Modele modele = modeleService.addModele(nom,
                nbrPlace,
                typeVoiture,
                active,
                file);
        return new ResponseEntity<>(modele, HttpStatus.CREATED);
    }


    @PutMapping("{idModele}")
    public ResponseEntity<Modele> editModele(@PathVariable Long idModele, @RequestBody ModelRequestDto modelRequestDto) throws BaseException {
        Modele modele = modeleService.editModele(idModele, modelRequestDto);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }

//    @GetMapping("hi")
//    public String horaire(){
//        Horaire h = Horaire.getAgenceHoraire();
//        return "Horaire: " + h.getStartTime() + " " + h.getEndTime();
//    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
