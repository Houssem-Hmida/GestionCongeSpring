package tn.esprit.gestionconges.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.User;

@Repository
public interface DemandeRepo extends JpaRepository<DemandeCongee, Long> {

    @Query("select d from DemandeCongee d where d.idDemande=:id")
    public DemandeCongee findDemandeById(@Param("id") Long id);
}
