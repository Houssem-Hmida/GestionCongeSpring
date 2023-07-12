package tn.esprit.gestionconges.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionconges.Repository.DemandeRepo;
import tn.esprit.gestionconges.entites.DemandeCongee;

import java.util.List;

@Service
public class DemandeImp implements IDemande{

    @Autowired
    DemandeRepo demandeRepo;

    @Override
    public DemandeCongee addDemande(DemandeCongee demandeCongee)
    {
        return  demandeRepo.save(demandeCongee);
    }


    @Override
    public List<DemandeCongee> getListDemande() {
        try {

            return demandeRepo.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error DemandeService in method getListDemande :: " + e.toString());

        }
    }


    @Override
    public DemandeCongee getDemandeById(Long id){
        try{
            if (id==null)
                return new DemandeCongee();
            DemandeCongee d =demandeRepo.findDemandeById(id);
            if (d==null)
                return new DemandeCongee();
            return d;
        }catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method getDemandeById :: " + e.toString());
        }
        }


    @Override
    public void deleteDemandeById(Long id) {
        try {

            demandeRepo.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error DemandeService in method deleteDemandeById :: " + e.toString());
        }
    }

    @Override
    public DemandeCongee updateDemandeById(DemandeCongee demande) {

        return  demandeRepo.save(demande);
//        try {
//            DemandeCongee updem = demandeRepo.findDemandeById(id);
//            updem.setTitle(demande.getTitle());
//            updem.setDescription(demande.getDescription());
//            updem.setDatedebut(demande.getDatedebut());
//            updem.setDatefin(demande.getDatefin());
//            updem.setUpdatedate(new Timestamp(new Date().getTime()));
//            updem.setId(id);
//
//            return demandeRepo.save(updem);
//        }
//        catch (Exception e) {
//            throw new IllegalStateException("Error DemandeService in method updateDemandeById :: " + e.toString());
//        }
    }





    }

