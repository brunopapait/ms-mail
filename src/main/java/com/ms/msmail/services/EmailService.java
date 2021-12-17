package com.ms.msmail.services;

import com.ms.msmail.enums.StatusEmail;
import com.ms.msmail.models.Email;
import com.ms.msmail.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

 @Autowired
 private EmailRepository emailRepository;

 @Autowired
 private JavaMailSender javaMailSender;

 public Email sendEmail(Email email) {
  email.setSendDataEmail(LocalDateTime.now());
  try {
   SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
   simpleMailMessage.setFrom(email.getEmailFrom());
   simpleMailMessage.setTo(email.getEmailTo());
   simpleMailMessage.setSubject(email.getSubject());
   simpleMailMessage.setText(email.getText());

   javaMailSender.send(simpleMailMessage);
   email.setStatusEmail(StatusEmail.SENT);
  } catch (MailException mailException) {
   email.setStatusEmail(StatusEmail.ERROR);
  } finally {
   return emailRepository.save(email);
  }
 }
}
