package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Modele, Long> {
    @Query("SELECT m FROM Modele m WHERE m.active = true")
    List<Modele> findAllActive();
}
