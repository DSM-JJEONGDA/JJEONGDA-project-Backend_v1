package com.example.loginpractice.service.auth;

import com.example.loginpractice.entity.certification.Certification;
import com.example.loginpractice.entity.certification.CertificationRepository;
import com.example.loginpractice.entity.certification.Certified;
import com.example.loginpractice.entity.user.User;
import com.example.loginpractice.entity.user.UserRepository;
import com.example.loginpractice.exception.*;
import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.service.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MailService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CertificationRepository certificationRepository;

    @Override
    public void sendEmail(EmailRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            throw new SendMessageFailedException();

        mailService.sendEmail(request.getEmail());
    }

    @Override
    @Transactional
    public void verifyAccount(EmailVerifiedRequest request){
        certificationRepository.findByEmail(request.getEmail())
                .filter(s -> request.getCode().equals(s.getCode()))
                .map(certification -> certificationRepository.save(certification.updateCertified(Certified.CERTIFIED)))
                .orElseThrow(CodeNotCorrectException::new);
    }

    @Override
    @Transactional
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getName()).isPresent())
            throw new UserNameAlreadyExistsException();

        Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(CodeAlreadyExpiredException::new);

        if (certification.getCertified() == (Certified.CERTIFIED)) {
            userRepository.save(User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build());
        } else throw new EmailNotCertifiedException();

        return "회원가입 성공";
    }
}
