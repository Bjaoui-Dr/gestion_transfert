package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Lieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieuRepository extends JpaRepository<Lieu, Long> {
}
