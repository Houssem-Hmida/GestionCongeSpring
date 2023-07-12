package tn.esprit.gestionconges.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionconges.entites.DemandeCongee;
import tn.esprit.gestionconges.entites.Feedback;
import tn.esprit.gestionconges.entites.User;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Long> {



    @Query("select f from Feedback f where f.id=:id")
    public Feedback findFeedBackById(@Param("id") Long id);
}
