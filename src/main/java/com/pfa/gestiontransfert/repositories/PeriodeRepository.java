package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PeriodeRepository extends JpaRepository<Periode, Long> {
    @Query("SELECT p.idPeriode FROM Periode p WHERE :date BETWEEN p.dateDebut AND p.dateFin")
    Long getIdPeriod(@Param("date")LocalDate date);
}
