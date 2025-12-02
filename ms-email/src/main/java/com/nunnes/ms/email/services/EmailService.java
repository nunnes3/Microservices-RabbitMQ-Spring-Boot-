package com.nunnes.ms.email.services;

import com.nunnes.ms.email.enums.StatusEmailEnum;
import com.nunnes.ms.email.models.EmailModel;
import com.nunnes.ms.email.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;


    public void sendEmail(EmailModel emailModel) {
        try {

            emailModel.setSentDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmailEnum.SENT);

        } catch (Exception e) {
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            emailRepository.save(emailModel);
        }
    }
}
