//package com.wah.whale.user.config;
//
//import com.wah.whale.user.service.UserDetailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private final UserDetailService userService;
//
//    @Bean   // security 비활성화
//    public WebSecurityCustomizer configure() {
//        return (web -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**"));
//    }
//
//    @Bean   // HTTP Request에 대한 웹 기반 보안 구성
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> auth // 인증, 인가 설정
//                        // 특정 경로에 대한 엑세스 설정
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/login"),
//                                new AntPathRequestMatcher("/signup"),
//                                new AntPathRequestMatcher("/user")
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin   // 폼 기반 로그인 설정
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/articles")
//                )
//                .logout(logOut -> logOut
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//                )
//                .csrf(AbstractHttpConfigurer::disable)  // csrf 임시 비활성화
//                .build();
//    }
//
//    @Bean   // 인증 관리자 관련 설정
//    // 사용자 정보 가져올 서비스 재정의 및 인증 방법 등 설정
//    public AuthenticationManager authenticationManager(HttpSecurity http
//                                                       ,BCryptPasswordEncoder bCryptPasswordEncoder
//                                                       ,UserDetailService userDetailService)
//        throws Exception {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);    // 사용자 정보 서비스 설정
//        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
//
//        return new ProviderManager(authProvider);
//    }
//
//    @Bean   // 패스워드 인코더로 사용할 빈 등록
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
