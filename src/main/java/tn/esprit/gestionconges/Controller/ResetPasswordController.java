package tn.esprit.gestionconges.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.gestionconges.entites.User;
import tn.esprit.gestionconges.message.requset.Mail;
import tn.esprit.gestionconges.message.requset.NewPassword;
import tn.esprit.gestionconges.message.requset.ResetPasswordRequest;
import tn.esprit.gestionconges.responce.AccountResponce;
import tn.esprit.gestionconges.security.services.EmailServiceImpl;
import tn.esprit.gestionconges.security.services.UserDetailsServiceImpl;
import tn.esprit.gestionconges.util.UserCode;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class ResetPasswordController {
	
    @Autowired
    private EmailServiceImpl emailService; 
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/checkeEmail")
	public AccountResponce resetPassword(@RequestBody ResetPasswordRequest passwordRequest) {
		//Boolean result = this.userDetailsServiceImpl.ifEmailExist(passwordRequest.getEmail());
		User user =this.userDetailsServiceImpl.getUserByMail(passwordRequest.getEmail());
		AccountResponce accountResponce=new AccountResponce();
		
			accountResponce.setResult(1);
		
		return accountResponce;
	}
	
	@PostMapping("/rezetPassword")
	public AccountResponce rezetpassword(@RequestBody NewPassword newPassword) {
		User user =this.userDetailsServiceImpl.getUserByMail(newPassword.getEmail());
		AccountResponce accountResponce=new AccountResponce();
		if(user!=null) {
		
				user.setPassword(encoder.encode(newPassword.getPassword()));
				userDetailsServiceImpl.editUser(user);
				accountResponce.setResult(1);
			
			
		}else {
			accountResponce.setResult(0);
		}
		return accountResponce;
	}
	@PostMapping("/compare")
	public AccountResponce compare(@RequestBody NewPassword newPassword) {
		AccountResponce accountResponce=new AccountResponce();
		if (newPassword.getPassword().equals(newPassword.getConfirmepassword())) {
			System.out.println("Les deux String sont égaux");
			accountResponce.setResult(1);
			
		}else {
			accountResponce.setResult(0);
		}
		
		return accountResponce;
	}
	
}
