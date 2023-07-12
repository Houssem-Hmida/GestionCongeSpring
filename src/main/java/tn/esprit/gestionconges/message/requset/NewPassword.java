package tn.esprit.gestionconges.message.requset;

public class NewPassword {
  private String email;
  private String password;
  private String confirmepassword;
 
  

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}


public String getConfirmepassword() {
	return confirmepassword;
}
public void setConfirmepassword(String confirmepassword) {
	this.confirmepassword = confirmepassword;
}
public NewPassword(String email, String code, String password, String confirmepassword) {
	super();
	this.email = email;
	this.password = password;
	this.confirmepassword = confirmepassword;
}
public NewPassword() {
	super();
	// TODO Auto-generated constructor stub
}
  

}
