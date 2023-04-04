//package ua.weeding.core.email;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class EmailSenderService {
//
//    private final JavaMailSender mailSender;
//
//    public void sendSimpleEmail(String toEmail,
//                                 String subject,
//                                 String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        String tekst = " Привіт, " +
//                "для того щоб відновити пароль буль ласка перейдіть за посиланням http://localhost:8080/forget?token=";
//        message.setFrom("leeboyofficial@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//        mailSender.send(message);
//        log.info("Email successfully sent {}",toEmail);
//    }
//}