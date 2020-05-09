package com.payday.email.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.payday.email.entities.Customer;
import com.payday.email.repositories.SendEmailRepository;

public class SendEmailService {
	
	 @Autowired
	 public JavaMailSender emailSender;
	
	private final SendEmailRepository sendEmailRepository;
	//private final JavaMailSender javaMailSender;

	
	public SendEmailService(SendEmailRepository sendEmailRepository) {

		this.sendEmailRepository = sendEmailRepository;
	}
	
	 void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        
        Optional<Customer> customer = sendEmailRepository.findByCustomerCode("1001");
        
        if (customer.isPresent())
        {
            msg.setTo(customer.get().getEmail());
            msg.setSubject("X bank Your Account has been created.");
            msg.setText("Account Number" + customer.get().getAccountNumber() + " Customer Number "
            		+customer.get().getCustomerName() + " Phone Number " + customer.get().getPhoneNumber());

          
            
            emailSender.send(msg);
		}
        
	}

}
