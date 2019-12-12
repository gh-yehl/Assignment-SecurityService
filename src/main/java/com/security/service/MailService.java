package com.security.service;

import com.security.model.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.activateURL}")
    private String activateURL;

    @Value("${spring.mail.message}")
    private String mailMessage;

    @Value("${spring.mail.subject}")
    private String mailSubject;

    public void sendMailHtml(UsersDTO usersDTO) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setFrom(from);
        helper.setTo(usersDTO.getEmail());
        //helper.setTo(mailBean.getReceiver().split(";"));
        helper.setSubject(mailSubject);
        helper.setText(mailMessage + activateURL+usersDTO.getUserName()+"@"+usersDTO.getId(), true);

        javaMailSender.send(mimeMessage);

    }
}
