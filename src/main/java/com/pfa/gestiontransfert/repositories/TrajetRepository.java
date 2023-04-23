package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Lieu;
import com.pfa.gestiontransfert.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajetRepository extends JpaRepository<Trajet, Long> {
    @Query("SELECT t.lieuArriver FROM Trajet t WHERE t.lieuDepart.id = :lieuDepartId AND t.active = true")
    List<Lieu> findLieuDepartByLieuArriverTrajetActive(Long lieuDepartId);
    List<Lieu> findDistinctLieuDepartByActiveIsTrue();
}
