package com.example.loginpractice.service;

import com.example.loginpractice.entity.RedisCode;
import com.example.loginpractice.entity.RedisRepository;
import com.example.loginpractice.exception.SendMailFailedException;
import com.example.loginpractice.payload.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendEmailService {
    private static final Integer CODE_LENGTH = 6;
    private static final Integer REDIS_TTL = 3 * 60;

    private static final StringBuilder key = new StringBuilder();
    private static final Random random = new Random();
    public static final String createKey = createKey();
    private final JavaMailSender mailSender;
    private final RedisRepository redisRepository;

    @Async
    public void execute(EmailRequest request) {
        try {
            String code = createCode();
            MimeMessage mail = mailSender.createMimeMessage();

            mail.addRecipients(Message.RecipientType.TO, request.getEmail());
            mail.setFrom(request.getEmail());
            mail.setSubject("Email 인증 요청 메일입니다.");
            mail.setText("6자리 인증코드 : " + code);

            redisRepository.findById(request.getEmail())
                    .or(() -> Optional.of(new RedisCode(request.getEmail(), code, REDIS_TTL)))
                    .ifPresent(verifyCode -> redisRepository.save(verifyCode.changeCode(code, REDIS_TTL)));

            sendMail(mail);
        } catch (MessagingException e) {
            e.getStackTrace();
            throw SendMailFailedException.EXCEPTION;
        }
    }
    //랜덤 숫자코드 생성
    private static String createKey() {
        key.setLength(0);
            for (int i = 0; i < CODE_LENGTH; i++) { // 인증코드 6자리
                 key.append((random.nextInt(10)));
            }
            return key.toString();
        }
        //코드 형식
        private String createCode() {
            return SendEmailService.createKey.substring(0, 3) + "-" + SendEmailService.createKey.substring(3, 6);
        }
        private void sendMail(MimeMessage mail) {
        mailSender.send(mail);
    }
}
