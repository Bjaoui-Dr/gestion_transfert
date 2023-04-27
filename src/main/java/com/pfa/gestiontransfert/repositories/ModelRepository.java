package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Modele, Long> {
}
