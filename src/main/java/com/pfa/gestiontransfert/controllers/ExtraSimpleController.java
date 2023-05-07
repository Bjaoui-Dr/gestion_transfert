package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSimpleRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSimple;
import com.pfa.gestiontransfert.services.ExtraSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extraSimple")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtraSimpleController {
    private final ExtraSimpleService extraSimpleService;

    @Autowired
    public ExtraSimpleController(ExtraSimpleService extraSimpleService) {
        this.extraSimpleService = extraSimpleService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExtraSimple> addExtraSimple(@RequestBody ExtraSimpleRequestDto extraSimpleRequestDto) {
        ExtraSimple extraSimple = extraSimpleService.addExtraSimple(extraSimpleRequestDto);
        return new ResponseEntity<>(extraSimple, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExtraSimple>> getExtraSimples() {
        List<ExtraSimple> extraSimples = extraSimpleService.getExtraSimples();
        return new ResponseEntity<>(extraSimples, HttpStatus.OK);
    }

    @GetMapping("{extraSimpleId}")
    public ResponseEntity<ExtraSimple> getExtraSimpleById(@PathVariable Long extraSimpleId) throws BaseException {
        ExtraSimple extraSimple = extraSimpleService.getExtraSimpleById(extraSimpleId);
        return new ResponseEntity<>(extraSimple, HttpStatus.OK);
    }

    @PutMapping("/{extraSimpleId}")
    public ResponseEntity<ExtraSimple> editExtraSimple(@PathVariable Long extraSimpleId,
                                                       @RequestBody ExtraSimpleRequestDto extraSimpleRequestDto) throws BaseException{
        ExtraSimple extraSimple = extraSimpleService.editExtraSimple(extraSimpleId, extraSimpleRequestDto);
        return new ResponseEntity<>(extraSimple, HttpStatus.OK);
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
