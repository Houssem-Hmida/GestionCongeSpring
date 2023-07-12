package tn.esprit.gestionconges.Controller;

import java.util.Collection;






import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.gestionconges.Repository.UserRepository;
import tn.esprit.gestionconges.entites.Code;
import tn.esprit.gestionconges.entites.User;
import tn.esprit.gestionconges.message.requset.ActiveAccount;
import tn.esprit.gestionconges.message.requset.ChangeInfoUserRequest;
import tn.esprit.gestionconges.message.requset.ChangePasswordRequest;
import tn.esprit.gestionconges.message.requset.LoginForm;
import tn.esprit.gestionconges.message.requset.Mail;
import tn.esprit.gestionconges.message.requset.SignUpForm;
import tn.esprit.gestionconges.message.requset.VerifRole;
import tn.esprit.gestionconges.responce.AccountResponce;
import tn.esprit.gestionconges.responce.JwtResponse;
import tn.esprit.gestionconges.security.jwt.JwtProvider;
import tn.esprit.gestionconges.security.services.EmailService;
import tn.esprit.gestionconges.security.services.UserActive;
import tn.esprit.gestionconges.security.services.UserDetailsServiceImpl;
import tn.esprit.gestionconges.util.UserCode;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	EmailService emailService;
	@Autowired
	UserDetailsServiceImpl userservice;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	@PostMapping("/signup")
	public AccountResponce registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	
		AccountResponce accountResponce=new AccountResponce();
		Boolean result1=userRepository.existsByUsername(signUpRequest.getUsername());

		Boolean result=userRepository.existsByEmail(signUpRequest.getEmail());
		if(result) {
			accountResponce.setResult(0);
		}else {

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
		
		userregister.setActive(1);
	
		String roless=signUpRequest.getRole();
        
		 if(roless=="admin") {	 
			 userregister.setRoles("ROLE_ADMIN");
		 }else if (roless=="pm") {
			 userregister.setRoles("ROLE_PM");
		 }else {
			 userregister.setRoles("ROLE_USER");
		 }
		 
		 
		userregister.setPassword(encoder.encode(signUpRequest.getPassword()));

		userRepository.save(userregister);
		accountResponce.setResult(1);
		}
		return accountResponce;
	
		}
	
	
	  @PutMapping("/changePassword/{username}")
	  public AccountResponce changePassword(@PathVariable String username,@RequestBody ChangePasswordRequest changePasswordRequest) {
		  User user=userRepository.findByUsername(username);
		  System.out.print(user.getPassword());
		  AccountResponce accountResponce=new AccountResponce(); 
		  if (this.bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(),user.getPassword())) {
			user.setPassword(this.bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
			accountResponce.setResult(1);
			userRepository.save(user);
		}else {
			accountResponce.setResult(0);
		}
		  
		return accountResponce;	  
		  
	  }
	  
	  @PutMapping("/changeInfo/{username}")
	  public User changeInfo(@PathVariable String username,@RequestBody ChangeInfoUserRequest changeInfo) {
		  User user=userRepository.findByUsername(username);
		  user.setPrenom(changeInfo.getPrenom());
		  user.setNom(changeInfo.getNom());
		  user.setDatenaissance(changeInfo.getDatenaissance());
		  user.setCodepostal(changeInfo.getCodepostal());
		  user.setTelephone(changeInfo.getTelephone());
		  user.setVille(changeInfo.getVille());
		  user.setCin(changeInfo.getCin());
		  user.setEmail(changeInfo.getEmail());
		  userRepository.save(user);
		return user;
		  
	  }
	  
	 
	  
	  
	  
	  
	  
		@PostMapping("/active")
		public UserActive getActiveUser(@RequestBody SignUpForm signUpRequest) {
			String enPassword = userservice.getPasswordByEmail(signUpRequest.getEmail()); //From DB
			Boolean result =encoder.matches(signUpRequest.getPassword(),enPassword); // Sure
	        UserActive userActive=new UserActive(); 
			if (result) {
				int act =userservice.getUserActive(signUpRequest.getEmail());
				if (act == 0) {
					String code =UserCode.getcode();
					Mail mail = new Mail(signUpRequest.getEmail(),code);
					emailService.sendCodeByMail(mail);
					User user =userservice.getUserByMail(signUpRequest.getEmail());
					user.getCode().setCode(code);
					userservice.editUser(user);
					}
				userActive.setActive(act);
			}else {
				userActive.setActive(-1);
			}
			return userActive;
		}
		
	@PostMapping("/activated")
	public AccountResponce activeaccount(@RequestBody ActiveAccount activeAccount) {
		User user =userservice.getUserByMail(activeAccount.getMail());
		AccountResponce accountResponce=new AccountResponce();
		if (user.getCode().getCode().equals(activeAccount.getCode())) {
			user.setActive(1);
			userservice.editUser(user);
			accountResponce.setResult(1);
		}else {
			accountResponce.setResult(0);
		}
		return accountResponce;
	}
	@PostMapping("/verifRole")
	public User verifRole(@RequestBody VerifRole verifRole){
       User user=userRepository.findByEmail(verifRole.getEmail());
    return user;
	}
	
	

	
}