package tn.esprit.gestionconges.Services;

import tn.esprit.gestionconges.entites.DemandeCongee;

import java.util.List;

public interface IDemande {
    DemandeCongee addDemande(DemandeCongee demandeCongee);

    List<DemandeCongee> getListDemande();

    DemandeCongee getDemandeById(Long id);

    void deleteDemandeById(Long id);

    DemandeCongee updateDemandeById(DemandeCongee demande);
}
