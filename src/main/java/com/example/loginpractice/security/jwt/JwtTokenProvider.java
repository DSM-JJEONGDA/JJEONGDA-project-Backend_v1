package com.example.loginpractice.security.jwt;

import com.example.loginpractice.exception.ExpiredAccessTokenException;
import com.example.loginpractice.exception.ExpiredRefreshTokenException;
import com.example.loginpractice.exception.IncorrectTokenException;
import com.example.loginpractice.exception.InvalidTokenException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.header}")
    private String HEADER;

    @Value("${jwt.prefix}")
    private String PREFIX;

    //토큰 유효기간
    private long ACCESS_TOKEN_VALID_TIME = 30 * 60 * 1000L; //30분
    private long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L; //1주

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    protected String setSecretKey(){
        return Base64.getEncoder().encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String createJwtAccessToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject(username)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createJwtRefreshToken(String username){
        return Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    // JWT 토큰 에서 인증 정보 조회
    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUsername(), userDetails.getAuthorities());
    }

    //토큰에서 회원 정보 추출
    public Claims getUsername(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new IncorrectTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredAccessTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
    //Request의 Header에서 token 값을 가져옵니다.
    public String resolveToken(HttpServletRequest request){
        String token = request.getHeader(HEADER);

        if(StringUtils.hasText(token) && token.startsWith(PREFIX)){
            return token.substring(7);
        }
        return null;
    }

    //토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        return !getUsername(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean isRefreshToken(String refreshToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return claims.get("type").equals("refresh") && claims.getExpiration().before(new Date());
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new IncorrectTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredRefreshTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
