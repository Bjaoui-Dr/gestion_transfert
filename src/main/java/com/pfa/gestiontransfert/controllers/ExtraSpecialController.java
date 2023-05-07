package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.ExtraSpecialRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.ExtraSpecial;
import com.pfa.gestiontransfert.services.ExtraSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extraSpecial")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtraSpecialController {
    private final ExtraSpecialService extraSpecialService;

    @Autowired
    public ExtraSpecialController(ExtraSpecialService extraSpecialService){
        this.extraSpecialService = extraSpecialService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExtraSpecial> addExtraSpecial(@RequestBody ExtraSpecialRequestDto extraSpecialRequestDto) {
        ExtraSpecial extraSpecial = extraSpecialService.addExtraSpecial(extraSpecialRequestDto);
        return new ResponseEntity<>(extraSpecial, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<ExtraSpecial>> getExtraSpecial() {
        List<ExtraSpecial> extraSpecials = extraSpecialService.getExtraSpecial();
        return new ResponseEntity<>(extraSpecials, HttpStatus.OK);
    }

    @GetMapping("/{extraSpecialId}")
    public ResponseEntity<ExtraSpecial> getExtraSpecialById(@PathVariable Long extraSpecialId) throws BaseException {
        ExtraSpecial extraSpecial = extraSpecialService.getExtraSpecialById(extraSpecialId);
        return new ResponseEntity<>(extraSpecial, HttpStatus.OK);
    }

    @PutMapping("/{extraSpecialId}")
    public ResponseEntity<ExtraSpecial> editExtraSpecial(@PathVariable Long extraSpecialId, @RequestBody ExtraSpecialRequestDto extraSpecialRequestDto) throws BaseException {
        ExtraSpecial extraSpecial = extraSpecialService.editExtraSpecial(extraSpecialId, extraSpecialRequestDto);
        return new ResponseEntity<>(extraSpecial, HttpStatus.OK);
    }


    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
