package tn.esprit.gestionconges.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionconges.Services.IDemande;
import tn.esprit.gestionconges.entites.DemandeCongee;

import java.util.List;

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


    @PostMapping("/addDemande")
    public DemandeCongee addDemande(@RequestBody DemandeCongee demandeCongee)
    {
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

    @PutMapping("/updatedemande")
    public DemandeCongee updateDemande(@RequestBody DemandeCongee demandeCongee){

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
