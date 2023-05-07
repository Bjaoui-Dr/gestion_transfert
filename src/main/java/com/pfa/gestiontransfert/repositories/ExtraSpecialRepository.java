package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.ExtraSpecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraSpecialRepository extends JpaRepository<ExtraSpecial, Long> {
}
