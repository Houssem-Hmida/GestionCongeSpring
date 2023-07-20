package tn.esprit.gestionconges.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tn.esprit.gestionconges.Repository.FeedBackRepo;
import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Feedback;

import java.util.List;

@Service
public class FeedBackImp implements IFeedBack{

    @Autowired
    FeedBackRepo feedBackRepo;




    @Override
    public List<Feedback> getListFeedback() {
        try {

            return feedBackRepo.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error FeedBackImp in method getListFeedback :: " + e.toString());

        }
    }



    @Override
    public Feedback addFeedBack(Feedback feedback) {

        return  feedBackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedBackById(Long id) {
        try{
            if (id==null)
                return new Feedback();
            Feedback f =feedBackRepo.findFeedBackById(id);
            if (f==null)
                return new Feedback();
            return f;
        }catch (Exception e) {
            throw new IllegalStateException("Error feedback service in method getFeedBackById :: " + e.toString());
        }
    }

    @Override
    public void deleteFeedBackById(Long id) {
        try {

            feedBackRepo.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error FeedBackImp in method deleteFeedBackById :: " + e.toString());
        }
    }

    @Override
    public Feedback updateFeedBackById(Feedback feedback) {
        return  feedBackRepo.save(feedback);
    }


}
