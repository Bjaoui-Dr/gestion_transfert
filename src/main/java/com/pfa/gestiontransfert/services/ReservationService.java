package com.pfa.gestiontransfert.services;

import com.pfa.gestiontransfert.dto.requestDto.PrixRequestDto;
import com.pfa.gestiontransfert.dto.requestDto.ReservationRequestDto;
import com.pfa.gestiontransfert.enumerations.Etat;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.Reservation;

import java.util.List;

public interface ReservationService {
    public Reservation addReservation(ReservationRequestDto reservationRequestDto) throws BaseException;
    public List<Reservation> listAllReservation();
    public Reservation getReservationById(Long idReservation) throws BaseException;
    public Reservation editReservation(Long idReservation, String etat) throws BaseException;
}
