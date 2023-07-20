package tn.esprit.gestionconges.Services;

import org.springframework.stereotype.Service;

import tn.esprit.gestionconges.Repository.ReclamationRepo;
import tn.esprit.gestionconges.Repository.UserRepository;
import tn.esprit.gestionconges.Services.Ireclamation;
@Service
public class ReclamationImpl implements Ireclamation {
    ReclamationRepo reclamationRepo ;
    UserRepository userRepo ;
   // @Override
   // public Reclamation Ajouterreclamation(Reclamation r, Long idUser) {
       // Users users = userRepo.findById(idUser).orElse(null);
       // r.setUsers(users);
      //  return reclamationRepo.save(r);
    //}

    //@Override
    //public User ajouterUser(Users user) {
        //return userRepo.save(user);
    //}
}
