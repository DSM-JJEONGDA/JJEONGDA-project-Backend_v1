package com.example.loginpractice.service.auth;

import com.example.loginpractice.entity.certification.Certification;
import com.example.loginpractice.entity.certification.CertificationRepository;
import com.example.loginpractice.entity.certification.Certified;
import com.example.loginpractice.entity.refreshToken.RefreshToken;
import com.example.loginpractice.entity.refreshToken.RefreshTokenRepository;
import com.example.loginpractice.entity.user.Role;
import com.example.loginpractice.entity.user.User;
import com.example.loginpractice.entity.user.UserRepository;
import com.example.loginpractice.exception.*;
import com.example.loginpractice.payload.request.EmailRequest;
import com.example.loginpractice.payload.request.EmailVerifiedRequest;
import com.example.loginpractice.payload.request.LoginRequest;
import com.example.loginpractice.payload.request.RegisterRequest;
import com.example.loginpractice.payload.response.TokenResponse;
import com.example.loginpractice.security.jwt.JwtTokenProvider;
import com.example.loginpractice.service.mail.MailService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L; //1주

    private final MailService mailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CertificationRepository certificationRepository;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
/*
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
*/
    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getName()).isPresent())
            throw UserNameAlreadyExistsException.EXCEPTION;
       /* Certification certification = certificationRepository.findByEmail(request.getEmail())
                .orElseThrow(CodeAlreadyExpiredException::new);

        if (certification.getCertified() == (Certified.CERTIFIED)) {*/
        {
            userRepository.save(User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.ROLE_USER)
                    .build());
        } //throw EmailNotCertifiedException.EXCEPTION;
    }

    @Override
    @Transactional
    public TokenResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw InvalidPasswordException.EXCEPTION;

        return createToken(request.getEmail());
    }

    @Override
    @Transactional
    public TokenResponse reissue(String refreshToken) {
        if(!tokenProvider.isRefreshToken(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        RefreshToken newRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(refresh -> refreshTokenRepository.save(
                        refresh.update(REFRESH_TOKEN_VALID_TIME)
                ))
                .orElseThrow(RefreshTokenNotFoundException::new);

        return new TokenResponse(tokenProvider.createJwtAccessToken(newRefreshToken.getUsername()), refreshToken);
    }

    @Transactional
    public TokenResponse createToken(String username) {
        String accessToken = tokenProvider.createJwtAccessToken(username);
        String refreshToken = tokenProvider.createJwtRefreshToken(username);

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .username(username)
                        .refreshToken(refreshToken)
                        .refreshExpiration(REFRESH_TOKEN_VALID_TIME)
                        .build());

        return new TokenResponse(accessToken, refreshToken);
    }

}
