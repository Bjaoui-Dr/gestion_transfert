package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {
}
