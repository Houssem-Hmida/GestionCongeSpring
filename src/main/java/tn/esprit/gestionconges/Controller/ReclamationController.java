package tn.esprit.gestionconges.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.gestionconges.Repository.ReclamationRepo;
import tn.esprit.gestionconges.Repository.UserRepository;
import tn.esprit.gestionconges.entites.Reclamation;
import tn.esprit.gestionconges.entites.User;
import tn.esprit.gestionconges.responce.ReclamationResponce;
import tn.esprit.gestionconges.responce.ReclamationResponceByUsername;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Api/Reclamation")
public class ReclamationController {
	@Autowired
private UserRepository userRepository;
	@Autowired 
private ReclamationRepo reclamationRepo;

	
	 @GetMapping(path = "")
	 public ReclamationResponce index(@RequestParam int page,@RequestParam int size) { 
		 ReclamationResponce reclamationResponce=new ReclamationResponce();
		 long Pages=reclamationRepo.count();
	     double totalPages= Math.ceil(Pages/5);
	     reclamationResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);
	    List<Reclamation> reclamations=reclamationRepo.findAllByOrderByUpdatedAtDesc(pageable).getContent();
	     reclamationResponce.setReclamations(reclamations);
		 return reclamationResponce;}
	 
	 
      @GetMapping(path = "username/{username}")
	 public ReclamationResponceByUsername indexusername(@PathVariable(value = "username")String username,@RequestParam int page,@RequestParam int size) { 
		 ReclamationResponceByUsername reclamationResponce=new ReclamationResponceByUsername();
		 long Pages=reclamationRepo.countByUsername(username);
	     double totalPages= Math.ceil(Pages/5);
	     reclamationResponce.setTotalPages(totalPages);
	     Pageable pageable=PageRequest.of(page,size);
	     List<Reclamation> reclamations=reclamationRepo.findByUsername(username,pageable).getContent(); 
	     reclamationResponce.setReclamations(reclamations);
	     return reclamationResponce;}
	
	 @GetMapping(path = "/{id}")
public ResponseEntity<Reclamation> show(@PathVariable(value = "id")Long id) {
    Optional<Reclamation> reclamation = reclamationRepo.findById(id);
    return reclamation
            .map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
	
	 @PostMapping("/{username}")
	 public Reclamation create(@PathVariable(value = "username")String username,@RequestBody Reclamation reclamation) {
		
			User user=userRepository.findByUsername(username);
			reclamation.setUsername(username);
			reclamation.setDescriptionReclamation(reclamation.getDescriptionReclamation());
			reclamation.setUsers(user);
			reclamation.setCreatedAt(new Date());
			reclamation.setUpdatedAt(null);
			
	        
			
			reclamationRepo.save(reclamation);
	    
	     return reclamation;
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Void> update(@RequestBody Reclamation  reclamationRequert, @PathVariable(value = "id")Long id) {
	     Optional<Reclamation> reclamation = reclamationRepo.findById(id);
	     if (reclamation.isPresent()) {
	         reclamation.get().setDescriptionReclamation(reclamationRequert.getDescriptionReclamation());
	         reclamation.get().setUsername(reclamationRequert.getUsername());
	     	reclamation.get().setUpdatedAt(new Date());
	     	reclamationRepo.save(reclamation.get());
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 }

	 @DeleteMapping("{id}")
	 public ResponseEntity<Void> destroy(@PathVariable(value = "id") Long id) {
	     Optional<Reclamation> reclamation = reclamationRepo.findById(id);
	     if (reclamation.isPresent()) {
	     	reclamationRepo.deleteById(id);
	         return ResponseEntity.status(HttpStatus.OK).build();
	     }
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	 }
	 
}
