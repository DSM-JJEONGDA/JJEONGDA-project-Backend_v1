package com.example.loginpractice.service;

import com.example.loginpractice.entity.CertificationRepository;
import com.example.loginpractice.entity.User;
import com.example.loginpractice.entity.UserRepository;
import com.example.loginpractice.exception.AlreadyUserExistException;
import com.example.loginpractice.exception.SendMailFailedException;
import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.service.mail.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MailServiceImpl sendEmailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CertificationRepository redisRepository;

    @Override
    public void exceute(EmailRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new SendMailFailedException();

        sendEmailService.execute(request.getEmail());
    }

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        try {
            userRepository.save(
                    User.builder()
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword()))
                            .build()
            );
            return "회원가입 성공";
        } catch (Exception e) {
            throw new AlreadyUserExistException();
        }
    }
}
