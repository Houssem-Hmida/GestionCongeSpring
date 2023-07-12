package tn.esprit.gestionconges.security.services;

import tn.esprit.gestionconges.message.requset.Mail;

public interface EmailService {
public void sendCodeByMail(Mail mail);
public void sendMailSuggestion(Mail mail);
public void sendMailSujet(Mail mail);
public void sendMailComment(Mail mail);
public void sendMailUsers(Mail mail);

}
