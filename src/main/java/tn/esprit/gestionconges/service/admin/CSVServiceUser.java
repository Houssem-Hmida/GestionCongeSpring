package tn.esprit.gestionconges.service.admin;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.gestionconges.Controller.CSVHelper.CSVHelperUser;
import tn.esprit.gestionconges.Repository.UserRepository;
import tn.esprit.gestionconges.entites.User;
@Service
public class CSVServiceUser {
	@Autowired
	  UserRepository userRepo;
	  public void save(MultipartFile file) {
		    try {
			      List<User> users = CSVHelperUser.csvToTutorials(file.getInputStream());
			      userRepo.saveAll(users);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }
	  public List<User> getAllUsers() {
		    return userRepo.findAll();
		  }
		 
}

