package com.pfa.gestiontransfert.controllers;

import com.pfa.gestiontransfert.dto.requestDto.ReservationRequestDto;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Reservation;
import com.pfa.gestiontransfert.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservationController {
    public final ReservationService reservationService;

    @Autowired
    ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationRequestDto reservationRequestDto) throws BaseException {
        Reservation reservation = reservationService.addReservation(reservationRequestDto);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
