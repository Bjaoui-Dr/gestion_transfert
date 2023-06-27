package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.ExtraRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Extra;
import com.pfa.gestiontransfert.repositories.ExtraRepository;
import com.pfa.gestiontransfert.services.ExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extra")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtraController {
    private final ExtraService extraService;

    @Autowired
    public ExtraController(ExtraService extraService) {
        this.extraService = extraService;
    }

    @PostMapping("/add")
    public ResponseEntity<Extra> addExtraSimple(@RequestBody ExtraRequestDto extraRequestDto) {
        Extra extraSimple = extraService.addExtra(extraRequestDto);
        return new ResponseEntity<>(extraSimple, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Extra>> getExtraSimples() {
        List<Extra> extras = extraService.getExtras();
        return new ResponseEntity<>(extras, HttpStatus.OK);
    }

    @GetMapping("{extraId}")
    public ResponseEntity<Extra> getExtraById(@PathVariable Long extraId) throws BaseException {
        Extra extra = extraService.getExtraById(extraId);
        return new ResponseEntity<>(extra, HttpStatus.OK);
    }

    @PutMapping("/{extraId}")
    public ResponseEntity<Extra> editExtraSimple(@PathVariable Long extraId,
                                                       @RequestBody ExtraRequestDto extraRequestDto) throws BaseException{
        Extra extra = extraService.editExtra(extraId, extraRequestDto);
        return new ResponseEntity<>(extra, HttpStatus.OK);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
