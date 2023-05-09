package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    @Query("SELECT t.lieuArriver FROM Trajet t WHERE t.lieuDepart.idLieu = :lieuDepartId AND t.active = true")
    List<Lieu> findLieuDepartByLieuArriverTrajetActive(@Param("lieuDepartId") Long lieuDepartId);

    @Query("SELECT t.lieuDepart FROM Trajet t WHERE t.active = true")
    List<Lieu> findDistinctLieuDepartByActiveIsTrue();
}
