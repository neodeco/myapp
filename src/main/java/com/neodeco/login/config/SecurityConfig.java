package com.neodeco.login.config;

import org.apache.tomcat.util.net.DispatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, AuthenticationProvider authProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(String.valueOf(OPTIONS), "/")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(String.valueOf(POST),"/register")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

/**
 * Spring Security 6.1
 * .csrf(AbstractHttpConfigurer::disable)
 * .authorizeHttpRequests( requestMatcherRegistry ->
 * requestMatcherRegistry
 * .dispatcherTypeMatchers(OPTIONS, DispatcherType.valueOf("/"))
 * .permitAll()
 * .requestMatchers( new AntPathRequestMatcher( "/api/v1/auth/**" ))
 * .authenticated() )
 * .sessionManagement( (session) -> session
 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
 * .authenticationProvider(authProvider)
 * .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
 */
