package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Modele;
import com.pfa.gestiontransfert.models.Periode;
import com.pfa.gestiontransfert.models.Prix;
import com.pfa.gestiontransfert.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixRepository extends JpaRepository<Prix, Long> {
    Prix findByModeleAndTrajetAndPeriode(Modele modele, Trajet trajet, Periode periode);
}
