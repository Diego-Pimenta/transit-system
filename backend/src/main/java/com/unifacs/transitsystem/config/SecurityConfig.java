package com.unifacs.transitsystem.config;

import com.unifacs.transitsystem.handler.CustomLogoutHandler;
import com.unifacs.transitsystem.security.api.JwtAuthenticationFilterApi;
import com.unifacs.transitsystem.security.api.JwtAuthorizationFilterApi;
import com.unifacs.transitsystem.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

//    private final CustomLogoutHandler logoutHandler;

    private static final String[] PUBLIC = {
            "/auth/login"
    };

    private static final String[] USER = {
            "/users/**",
            "/search/**"
    };

    private static final String[] WORKER = {
            "/tickets/**",
            "/vehicles/**",
            "/driver-tickets/**"
    };

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors->cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth->auth.requestMatchers(PUBLIC)
                                .permitAll()
                                .requestMatchers(USER).hasAnyRole("WORKER", "USER")
                                .requestMatchers(WORKER).hasRole("WORKER")
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // creating new objects may cause performance issues
                .addFilter(new JwtAuthenticationFilterApi(authenticationManager(http)))
                .addFilter(new JwtAuthorizationFilterApi(authenticationManager(http)))
                .exceptionHandling(
                        e->e.accessDeniedHandler(
                                (request, response, accessDeniedException)->response.setStatus(403)
                        )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
//                .logout(
//                        l->l.logoutUrl("/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler(
//                                        ((request, response, authentication)->SecurityContextHolder.clearContext())
//                                )
//                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                // sample ignored specified requests
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
