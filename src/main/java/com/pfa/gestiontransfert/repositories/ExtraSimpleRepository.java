package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.ExtraSimple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraSimpleRepository extends JpaRepository<ExtraSimple, Long> {
}
