package com.pfa.gestiontransfert.repositories;

import com.pfa.gestiontransfert.models.Modele;
import com.pfa.gestiontransfert.models.Periode;
import com.pfa.gestiontransfert.models.Prix;
import com.pfa.gestiontransfert.models.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixRepository extends JpaRepository<Prix, Long> {

    @Query("SELECT p.prix FROM Prix p WHERE p.modele.idModel = :idModel AND p.trajet.idTrajet = :idTrajet AND p.periode.idPeriode = :idPeriode")
    double findPrixByModeleAndTrajetAndPeriode(@Param("idModel") Long idModele, @Param("idTrajet") Long idTrajet, @Param("idPeriode") Long idPeriode);
}
