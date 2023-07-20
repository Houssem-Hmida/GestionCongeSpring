package tn.esprit.gestionconges.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionconges.Repository.FeedBackRepo;
import tn.esprit.gestionconges.Services.IFeedBack;
import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Feedback;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feedback")
public class FeedBackController {
    @Autowired
    IFeedBack iFeedBack;
FeedBackRepo feedBackRepo;


    @GetMapping(value = "getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feedback> getListDemande() {
        try {

            return iFeedBack.getListFeedback();


        } catch (Exception e) {
            throw new IllegalStateException("Error FeedBackController in method getListDemande :: " + e.toString());
        }
    }

////
//    @PostMapping("/addFeedBack")
//    public Feedback addFeedBack(@RequestBody Feedback feedback)
//    {
//        return iFeedBack.addFeedBack(feedback)   ;
//    }
//


    @PostMapping("/add/{username}")
    public Feedback addFeedBack(@PathVariable(value = "username")String username, @RequestBody Feedback feedback)
    {
System.out.println(feedback.getDescription());
        feedback.setUsername(username);
feedback.setDescription(feedback.getDescription());

        return iFeedBack.addFeedBack(feedback);

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

//    @PutMapping("/updatefeedback")
//    public Feedback updategetFeedbackById(@RequestBody Feedback feedback){
//
//        return  iFeedBack.updateFeedBackById(feedback);
//    }



    @PutMapping("{id}")
    public Feedback updategetFeedbackById(@PathVariable(value = "id") Long id,@RequestBody Feedback  feed){

        Feedback feedback = iFeedBack.getFeedBackById(id);
        feedback.setDate_feed(feed.getDate_feed());
        feedback.setUsername(feed.getUsername());
        feedback.setDescription(feed.getDescription());
        System.out.println(feed.getDescription());
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
