package tn.esprit.gestionconges.Services;

import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Feedback;

import java.util.List;

public interface IFeedBack {
    Feedback addFeedBack(Feedback feedback);

    Feedback getFeedBackById(Long id);

    void deleteFeedBackById(Long id);

    Feedback updateFeedBackById(Feedback feedback);

    List<Feedback> getListFeedback();
}
