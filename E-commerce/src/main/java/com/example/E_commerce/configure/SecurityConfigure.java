package com.example.E_commerce.configure;

import com.example.E_commerce.Constant.USER_ROLE;
import com.example.E_commerce.service.security.service.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigure {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private Filter filter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/user/register/**",
                                "/api/user/login",
                                "/api/product/findbyid/{productId}",
                                "/api/product/findall"
                        ).permitAll()

                        .requestMatchers(
                                "/api/product/updateproduct/{productId}",
                                "/api/product/delete/{productId}",
                                "/api/product/add",
                                "/api/order/update-order-status"
                        ).hasRole(USER_ROLE.SELLER.name())

                        .requestMatchers(
                                "/api/order/placeorder",
                                "/api/cart/**"
                        ).hasAnyRole(USER_ROLE.CUSTOMER.name())

                        .requestMatchers("/api/order/findall")
                        .hasAnyRole(USER_ROLE.SELLER.name(), USER_ROLE.CUSTOMER.name())

                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(userDetailsService);
       return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
