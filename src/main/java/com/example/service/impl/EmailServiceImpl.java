package com.example.service.impl;

import com.example.object.request.EmailRequest;
import com.example.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by LKW on 2017/5/21.
 */

@Component("emailService")
@Transactional
public class EmailServiceImpl/* extends BaseServiceImpl<Long, BaseEntity>*/ implements EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(EmailRequest emailRequest) throws Exception {

        MimeMessagePreparator preparator = getMessagePreparator(emailRequest);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }


    }

    private MimeMessagePreparator getMessagePreparator(EmailRequest emailRequest) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setContent(emailRequest.getEmailContent(), "text/html; charset=utf-8");
                mimeMessage.setFrom(emailRequest.getSendAddress());
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(emailRequest.getReceiverAddress()));
                //mimeMessage.setText(emailRequest.getEmailContent());
                mimeMessage.setSubject(emailRequest.getSubject());
            }
        };
        return preparator;
    }
}
