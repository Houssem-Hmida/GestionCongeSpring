package tn.esprit.gestionconges.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionconges.Repository.DemandeRepo;
import tn.esprit.gestionconges.Services.IDemande;
import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Reclamation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Demande")
public class DemandeController {

    @Autowired
    IDemande iDemande;


    @GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DemandeCongee> getListDemande() {
        try {

            return iDemande.getListDemande();


        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method getListDemande :: " + e.toString());
        }
    }


    @PostMapping("/add/{username}")
    public DemandeCongee addDemande(@PathVariable(value = "username")String username,@RequestBody DemandeCongee demandeCongee)
    {
       System.out.println(demandeCongee.getTypeConge());
        demandeCongee.setUsername(username);

        return iDemande.addDemande(demandeCongee);
    }

    @GetMapping(value = "/finddemande/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandeCongee> getDemandeById(@PathVariable("id") Long id) {
        try {

            DemandeCongee demande = iDemande.getDemandeById(id);
            return new ResponseEntity<>(demande, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method getDemandeById :: " + e.toString());
        }
    }

    @PutMapping("{id}")
    public DemandeCongee updateDemande(@PathVariable(value = "id") Long id,@RequestBody DemandeCongee  demande){
        DemandeCongee demandeCongee = iDemande.getDemandeById(id);
        demandeCongee.setDate_debut(demande.getDate_debut());
        demandeCongee.setUsername(demande.getUsername());
        demandeCongee.setDate_fin(demande.getDate_fin());
        demandeCongee.setTypeConge(demande.getTypeConge());
        demandeCongee.setReason(demande.getReason());
        demandeCongee.setSatuts(demande.getSatuts());
        demandeCongee.setUsername(demandeCongee.getUsername());
        return  iDemande.updateDemandeById(demandeCongee);
    }
    @PutMapping("/approve/{id}")
    public DemandeCongee updateDemandeStatus(@PathVariable(value = "id") Long id,@RequestBody DemandeCongee  demande){
        DemandeCongee demandeCongee = iDemande.getDemandeById(id);

        demandeCongee.setSatuts(demande.getSatuts());
        return  iDemande.updateDemandeById(demandeCongee);
    }

    @DeleteMapping(value = "deletedemande/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDemandeById(@PathVariable("id") Long id) {
        try {
            iDemande.deleteDemandeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error DemandeController in method deleteDemandeById :: " + e.toString());
        }
    }




}
