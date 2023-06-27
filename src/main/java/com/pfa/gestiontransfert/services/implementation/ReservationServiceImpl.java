package com.pfa.gestiontransfert.services.implementation;

import com.pfa.gestiontransfert.dto.requestDto.PrixRequestDto;
import com.pfa.gestiontransfert.dto.requestDto.ReservationRequestDto;
import com.pfa.gestiontransfert.enumerations.Etat;
import com.pfa.gestiontransfert.exceptions.BaseException;
import com.pfa.gestiontransfert.models.*;
import com.pfa.gestiontransfert.repositories.ReservationRepository;
import com.pfa.gestiontransfert.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModeleService modeleService;
    private final PeriodeService periodeService;
    private final TrajetServiceImpl trajetService;
    private final ExtraService extraService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ModeleService modeleService,
                                  PeriodeService periodeService,
                                  TrajetServiceImpl trajetService,
                                  ExtraService extraService){
        this.reservationRepository = reservationRepository;
        this.modeleService = modeleService;
        this.periodeService = periodeService;
        this.trajetService = trajetService;
        this.extraService = extraService;
    }

    @Override
    public Reservation addReservation(ReservationRequestDto reservationRequestDto) throws BaseException {
        Reservation reservation = new Reservation();
        reservation.setDateCreation(LocalDateTime.now());
        reservation.setEtat(Etat.EN_ATTENDE);
        Trajet trajetDepart = trajetService.getTrajetById(reservationRequestDto.getIdTrajetDepart());
        reservation.setTrajetDepart(trajetDepart);
        reservation.setDateAndTimeAller(reservationRequestDto.getDateAndTimeAller());
        reservation.setNbrVoyageurs(reservationRequestDto.getNbrVoyageurs());
        Modele modele = modeleService.getModeleById(reservationRequestDto.getIdModel());
        reservation.setModel(modele);
        reservation.setQteModele(reservationRequestDto.getQteModele());

        LocalDate dateAller = reservationRequestDto.getDateAndTimeAller().toLocalDate();
        LocalTime timeAller = reservationRequestDto.getDateAndTimeAller().toLocalTime();
        Long idPeriode = periodeService.getIdPeriod(dateAller);
        Periode periodeAller = periodeService.getPeriodeById(idPeriode);

        double prix = 0;
        reservation.setTotale(prix);
        reservation.addFees(trajetDepart.getPrice());
        reservation.addFees(modele.getPrice());
        reservation.addFees(periodeAller.getPrice());

        // trajet aller
        // Add all extra simple
        addExtraSimple(reservationRequestDto, reservation);

        // Horaire
        LocalTime startTime = Horaire.getAgenceHoraire().getStartTime();
        LocalTime endTime = Horaire.getAgenceHoraire().getEndTime();

        //ajouter extra simple
        addExtraSpecialHorsTime(timeAller,
                startTime,
                endTime,
                reservation);

        addExtraSpecialBeforeDate(dateAller,reservation);

        // trajet retour
//        if(reservationRequestDto.getIdTrajetRetour() != null){
//            LocalTime timeRetour = reservationRequestDto.getDateAndTimeRetour().toLocalTime();
//            Trajet trajetRetour = trajetService.getTrajetById(reservationRequestDto.getIdTrajetRetour());
//            reservation.setTrajetRetour(trajetRetour);
//            reservation.setDateAndTimeRetour(reservationRequestDto.getDateAndTimeRetour());
//            double prixRetour = prixService.getPrixByModelTrajetPeriode(reservationRequestDto.getIdModel(),
//                    reservationRequestDto.getIdTrajetRetour(),
//                    idPeriode);
//            reservation.addExtraFees(prixRetour);
//            addExtraSpecialHorsTime(timeRetour,
//                    startTime,
//                    endTime,
//                    reservation,
//                    prixRetour);
//        }

        return reservationRepository.save(reservation);
    }

    private void addExtraSimple(ReservationRequestDto reservationRequestDto,
                                Reservation reservation) throws BaseException {
        List<Extra> extras = new ArrayList<>();
        for(Long idExtra : reservationRequestDto.getIdExtras()){
            Extra extraSimples = extraService.getExtraById(idExtra);
            reservation.addFees(extraSimples.getTarif());
            reservation.addExtra(extraSimples);
        }
    }

    public void addExtraSpecialHorsTime(LocalTime timeAller,
                                        LocalTime startTime,
                                        LocalTime endTime,
                                        Reservation reservation) throws BaseException {
        //LocalTime timeAller = reservationRequestDto.getDateAndTimeAller().toLocalTime();
        if(timeAller.isBefore(startTime) || timeAller.isAfter(endTime)) {
            Extra extraSpecialHorsTime = extraService.getExtraById(3L);
            reservation.addExtra(extraSpecialHorsTime);
            reservation.addFees(extraSpecialHorsTime.getTarif());
        }
    }

    public void addExtraSpecialBeforeDate(LocalDate dateAller,
                                          Reservation reservation) throws BaseException {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), dateAller);
        if (daysBetween <=2){
            Extra extraSpecialBeforeDate = extraService.getExtraById(4L);
            reservation.addExtra(extraSpecialBeforeDate);
            reservation.addFees(extraSpecialBeforeDate.getTarif());
        }
    }

    @Override
    public List<Reservation> listAllReservation() {
        return null;
    }

    @Override
    public Reservation getReservationById(Long idReservation) {
        return null;
    }

    @Override
    public Reservation editReservation(Long idReservation, PrixRequestDto prixRequestDto) throws BaseException {
        return null;
    }
}
