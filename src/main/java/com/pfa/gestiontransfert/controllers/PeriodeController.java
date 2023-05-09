package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.PeriodeRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Periode;
import com.pfa.gestiontransfert.services.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/period")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PeriodeController {
    private final PeriodeService periodeService;

    @Autowired
    public PeriodeController(PeriodeService periodeService){
        this.periodeService = periodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Periode> addPeriode(@RequestBody PeriodeRequestDto periodeRequestDto) {
        Periode periode = periodeService.addPeriode(periodeRequestDto);
        return new ResponseEntity<>(periode, HttpStatus.CREATED);
    }

    @GetMapping("/{periodeId}")
    public ResponseEntity<Periode> getPeriodeById(@PathVariable Long periodeId) throws BaseException {
        Periode periode = periodeService.getPeriodeById(periodeId);
        return new ResponseEntity<>(periode, HttpStatus.OK);
    }

    @GetMapping("idPeriode/{date}")
    public ResponseEntity<Long> getPeriodeById(@PathVariable LocalDate date) throws BaseException {
        Long periodeId = periodeService.getIdPeriod(date);
        return new ResponseEntity<>(periodeId, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Periode>> getAllPeriodes() {
        List<Periode> periodes = periodeService.getAllPeriodes();
        return new ResponseEntity<>(periodes, HttpStatus.OK);
    }

    @PutMapping("/{periodeId}")
    public ResponseEntity<Periode> editePeriode(@PathVariable Long periodeId, @RequestBody PeriodeRequestDto periodeRequestDto) throws BaseException {
        Periode periode = periodeService.editePeriode(periodeId, periodeRequestDto);
        return new ResponseEntity<>(periode, HttpStatus.OK);
    }



    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<Object> handleException(BaseException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
