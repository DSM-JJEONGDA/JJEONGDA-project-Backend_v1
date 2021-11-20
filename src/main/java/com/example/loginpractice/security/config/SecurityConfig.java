package com.example.loginpractice.security.config;

import com.example.loginpractice.error.ExceptionHandlerFilter;
import com.example.loginpractice.security.jwt.JwtTokenFilter;
import com.example.loginpractice.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    // authenticationManager를 Bean 등록합니다.

   /* @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //rest api 만을 고려하여 기본설정은 해제 하겠습니다.
                .csrf().disable() //csrf 보안 토큰 disable 처리.
                .formLogin().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //토큰기반 인증이므로 세션 역시 사용하지 않음.

                .and()
                .authorizeRequests() //요청에 대한 사용권한 체크

                .antMatchers("/send").permitAll()
                .antMatchers("/email").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/reissue").permitAll()
                .anyRequest().authenticated()

                //.antMatchers("/**").permitAll() //그 외 나머지 요청은 누구나 접근 가능
                .and()
                .apply(new FilterConfig(jwtTokenProvider, exceptionHandlerFilter));
    }
}
