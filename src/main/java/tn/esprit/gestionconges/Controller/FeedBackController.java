package tn.esprit.gestionconges.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionconges.Services.IFeedBack;
import tn.esprit.gestionconges.entites.Feedback;

import java.util.List;

@RestController
public class FeedBackController {
    @Autowired
    IFeedBack iFeedBack;



    @GetMapping(value = "getallfeedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feedback> getListDemande() {
        try {

            return iFeedBack.getListFeedback();


        } catch (Exception e) {
            throw new IllegalStateException("Error FeedBackController in method getListDemande :: " + e.toString());
        }
    }

//
    @PostMapping("/addFeedBack")
    public Feedback addFeedBack(@RequestBody Feedback feedback)
    {
        return iFeedBack.addFeedBack(feedback)   ;
    }

    @GetMapping(value = "/findfeedback/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") Long id) {
        try {

            Feedback feedback = iFeedBack.getFeedBackById(id);
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error FeedBackController in method getFeedbackById :: " + e.toString());
        }
    }

    @PutMapping("/updatefeedback")
    public Feedback updategetFeedbackById(@RequestBody Feedback feedback){

        return  iFeedBack.updateFeedBackById(feedback);
    }

    @DeleteMapping(value = "deletefeedback/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteFeedBackById(@PathVariable("id") Long id) {
        try {
            iFeedBack.deleteFeedBackById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new IllegalStateException("Error FeedBackController in method deleteFeedbackById :: " + e.toString());
        }
    }

}
