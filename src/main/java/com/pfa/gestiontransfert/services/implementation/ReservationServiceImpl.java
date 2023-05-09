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
    private final PrixService prixService;
    private final ExtraSimpleService extraSimpleService;
    private final ExtraSpecialService extraSpecialService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  ModeleService modeleService,
                                  PeriodeService periodeService,
                                  TrajetServiceImpl trajetService,
                                  PrixService prixService,
                                  ExtraSimpleService extraSimpleService,
                                  ExtraSpecialService extraSpecialService){
        this.reservationRepository = reservationRepository;
        this.modeleService = modeleService;
        this.periodeService = periodeService;
        this.trajetService = trajetService;
        this.prixService = prixService;
        this.extraSimpleService = extraSimpleService;
        this.extraSpecialService = extraSpecialService;
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

        double prix = prixService.getPrixByModelTrajetPeriode(reservationRequestDto.getIdModel(),
                reservationRequestDto.getIdTrajetDepart(),
                idPeriode);

        reservation.setTotale(prix);

        // Add all extra simple
        addExtraSimple(reservationRequestDto, reservation);

        // Horaire
        LocalTime startTime = Horaire.getAgenceHoraire().getStartTime();
        LocalTime endTime = Horaire.getAgenceHoraire().getEndTime();

        // trajet aller
        addExtraSpecialHorsTime(timeAller,
                startTime,
                endTime,
                reservation,
                prix);

        addExtraSpecialBeforeDate(dateAller,reservation,prix);

        // trajet retour
        if(reservationRequestDto.getIdTrajetRetour() != null){
            LocalTime timeRetour = reservationRequestDto.getDateAndTimeRetour().toLocalTime();
            Trajet trajetRetour = trajetService.getTrajetById(reservationRequestDto.getIdTrajetRetour());
            reservation.setTrajetRetour(trajetRetour);
            reservation.setDateAndTimeRetour(reservationRequestDto.getDateAndTimeRetour());
            double prixRetour = prixService.getPrixByModelTrajetPeriode(reservationRequestDto.getIdModel(),
                    reservationRequestDto.getIdTrajetRetour(),
                    idPeriode);
            reservation.addExtraFees(prixRetour);
            addExtraSpecialHorsTime(timeRetour,
                    startTime,
                    endTime,
                    reservation,
                    prixRetour);
        }

        return reservationRepository.save(reservation);
    }

    private void addExtraSimple(ReservationRequestDto reservationRequestDto,
                                Reservation reservation) throws BaseException {
        List<Extra> extraSimples = new ArrayList<>();
        for(Long idExtra : reservationRequestDto.getIdExtras()){
            ExtraSimple extraSimple = extraSimpleService.getExtraSimpleById(idExtra);
            reservation.addExtraFees(extraSimple.getTarif());
            reservation.addExtra(extraSimple);
        }
    }

    public void addExtraSpecialHorsTime(LocalTime timeAller,
                                        LocalTime startTime,
                                        LocalTime endTime,
                                        Reservation reservation,
                                        double prix) throws BaseException {
        //LocalTime timeAller = reservationRequestDto.getDateAndTimeAller().toLocalTime();
        if(timeAller.isBefore(startTime) || timeAller.isAfter(endTime)) {
            ExtraSpecial extraSpecialHorsTime = extraSpecialService.getExtraSpecialById(3L);
            reservation.addExtra(extraSpecialHorsTime);
            double fees = extraSpecialHorsTime.getPercent();
            double extraCostHorsTime = prix * fees /100;
            reservation.addExtraFees(extraCostHorsTime);
        }
    }

    public void addExtraSpecialBeforeDate(LocalDate dateAller,
                                          Reservation reservation,
                                          double prix) throws BaseException {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), dateAller);
        if (daysBetween <=2){
            ExtraSpecial extraSpecialBeforeDate = extraSpecialService.getExtraSpecialById(4L);
            double fees = extraSpecialBeforeDate.getPercent();
            reservation.addExtra(extraSpecialBeforeDate);
            double extraCostBeforeDate = prix * fees / 100;
            reservation.addExtraFees(extraCostBeforeDate);
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
