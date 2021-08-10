package com.ms.msmail.controllers;

import com.ms.msmail.dtos.EmailDto;
import com.ms.msmail.models.Email;
import com.ms.msmail.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailController {

  @Autowired
  private EmailService emailService;

  @PostMapping("sendEmail")
  public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
    Email email = new Email();
    BeanUtils.copyProperties(emailDto, email);

    emailService.sendEmail(email);
    return new ResponseEntity<>(email, HttpStatus.CREATED);
  }
}
