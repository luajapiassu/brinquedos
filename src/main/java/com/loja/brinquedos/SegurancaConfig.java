package com.loja.brinquedos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SegurancaConfig {

    @Bean
    @Order(1)
    SecurityFilterChain securityFilterChainSite(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/", "/login", "/logout", "/error", "/css/**", "/painel/**", "/brinquedos", "/brinquedos/**")
                .csrf(csrf -> csrf.ignoringRequestMatchers("/brinquedos", "/brinquedos/**"))
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/", "/login", "/error", "/css/**").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/brinquedos", "/brinquedos/**").hasAnyRole("USER", "ADMIN");
                            authorizeConfig.requestMatchers("/brinquedos", "/brinquedos/**").hasRole("ADMIN");
                            authorizeConfig.requestMatchers("/painel/admin/**").hasRole("ADMIN");
                            authorizeConfig.anyRequest().authenticated();
                        })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/painel", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/?logout")
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
