package tn.esprit.gestionconges.Controller.admin;
import java.io.IOException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;





import com.fasterxml.jackson.annotation.JsonProperty;


import tn.esprit.gestionconges.Repository.UserRepository;
import tn.esprit.gestionconges.entites.User;
import tn.esprit.gestionconges.message.requset.SignUpForm;
import tn.esprit.gestionconges.responce.ResponseMessage;
import tn.esprit.gestionconges.responce.UserResponce;
import tn.esprit.gestionconges.security.jwt.JwtProvider;
import tn.esprit.gestionconges.service.admin.AdminUserService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/admin")

public class AdminController {
	public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String ADMIN_ACCESS = "ROLE_ADMIN";
    public static final String MODERATOR_ACCESS = "ROLE_MODERATOR";
	@Autowired
	  private AdminUserService userService;

	@Autowired
	UserRepository userRepository;


   
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	   @JsonProperty("admin")
	
	   
	    @GetMapping(path = "/user",produces= {MediaType.APPLICATION_JSON_VALUE})
	  public UserResponce getAllUsers(@RequestParam int page,@RequestParam int size) {
		   UserResponce userResponce=new UserResponce();
		   long Pages=userRepository.count();
		   double totalPages= Math.ceil(Pages/5);
		   userResponce.setTotalPages(totalPages);
		   Pageable pageable=PageRequest.of(page,size);	 
		     List<User> users=userRepository.findAll(pageable).getContent();
		     userResponce.setUsers(users);
		     return userResponce;
		  }
	   
	   
	
		
	    @GetMapping(path = "/user/{id}")
	    public Optional<User> finduser(@PathVariable Long id) {
	        return userService.getOne(id);
	    }
	    

	    @DeleteMapping(path = "/user/{id}")
	    public ResponseEntity<Void> destroy(@PathVariable Long id) {
	        return userService.delete(id);
	    }
	    
	    @PutMapping(path = "/user/{id}")
	    public User updateUser(@RequestBody User user,@PathVariable Long id){
	    
	     return userService.updateUser(user, id);
	     	 
	    }
	    
	    @GetMapping(path="ville/{ville}")
	    public List<User> getByVille(@PathVariable String ville) {
	    	return userRepository.findByVille(ville);
	    }
	    
	    @GetMapping(path="ByCin/{cin}")
	    public User getByCin(@PathVariable String cin) {
	    	return userRepository.findByCin(cin);
	    }
	
	    
	 
	    
	    @GetMapping(path="/{username}")
	    public User getusername(@PathVariable String username) {
	    	 User user=userRepository.findByUsername(username);
	    	 return user;
	    }
	     	
	    @PostMapping("/user")
	    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpForm signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
						HttpStatus.BAD_REQUEST);
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
						HttpStatus.BAD_REQUEST);
			}

			// Creating user's account
			User userregister = new User();
			userregister.setPrenom(signUpRequest.getPrenom());
			userregister.setNom(signUpRequest.getNom());
			userregister.setUsername(signUpRequest.getUsername());
			userregister.setDatenaissance(signUpRequest.getDatenaissance());
			userregister.setCodepostal(signUpRequest.getCodepostal());
			userregister.setTelephone(signUpRequest.getTelephone());
			userregister.setVille(signUpRequest.getVille());
			userregister.setDateajout(signUpRequest.getDateajout());
			userregister.setCin(signUpRequest.getCin());
			userregister.setEmail(signUpRequest.getEmail());
			userregister.setRoles(signUpRequest.getRole());
	        
			 if((signUpRequest.getRole())=="admin") {	 
				 userregister.setRoles(ADMIN_ACCESS);
			 }else if ((signUpRequest.getRole())=="pm") {
				 userregister.setRoles(MODERATOR_ACCESS);
			 }else {
				 userregister.setRoles(DEFAULT_ROLE);
			 }
			
			     
			
			userregister.setPassword(encoder.encode(signUpRequest.getPassword()));
		
			 
			userRepository.save(userregister);

			return new ResponseEntity<>(new ResponseMessage("l'utilisateur"+ signUpRequest.getPrenom() + " is registered successfully!"), HttpStatus.OK);
			 
	    
      }
	    
 

  
}
