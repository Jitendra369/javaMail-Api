package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/user")
    public String welcome(){
        return "hellow welcome user";
    }

    @PostMapping("/sendemail")
    @CrossOrigin
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
        boolean status = this.emailService.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(), emailRequest.getTo());
        System.out.println("inside the email controller");
        System.out.println(emailRequest);
        if (status){
                return ResponseEntity.ok(new EmailResponse("Email send Sucessfully"));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email has not been Send"));
        }

    }

}
