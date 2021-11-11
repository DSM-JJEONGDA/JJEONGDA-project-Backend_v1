package com.example.loginpractice.service;

import com.example.loginpractice.entity.Certification;
import com.example.loginpractice.entity.CertificationRepository;
import com.example.loginpractice.exception.SendMailFailedException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    @Value("${code.exp}")
    private Integer CODE_EXP;

    private final JavaMailSender javaMailSender;
    private final CertificationRepository redisRepository;

    @Transactional
    public String sendCode(String email) {

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            String code = getCode(createKey());
            message.setFrom("sdpthf@gmail.com");
            message.addRecipients(Message.RecipientType.TO,email);
            message.setSubject("[Test 이메일 인증]");
            message.setText(code);
        } catch (MessagingException e) {
            e.getStackTrace();
            throw new SendMailFailedException();
        }

    }

    @Transactional
    @Override
    public void sendEmail(String email){
        redisRepository.findByEmail(email)
                .map(redisCode -> redisRepository.save(redisCode.updateCode(sendCode(email))))
                .orElseGet(() -> redisRepository.save(Certification.builder()
                                .code(sendCode(email))
                                .email(email)
                                .codeExp(CODE_EXP)
                                .certificate(Certified.NOT_CERTIFIED)
                                .build())
                );
    }

    public String createKey(){
        return RandomStringUtils.randomNumberic(6);
    }

    public String getCode(String key){
        return key.substring(0, 3) + "-" + key.substring(3, 6);
    }

}
