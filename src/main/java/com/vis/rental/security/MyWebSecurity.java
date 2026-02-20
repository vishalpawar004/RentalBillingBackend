package com.vis.rental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class MyWebSecurity {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider(UserDetailsService userDetailsService,
                                                  PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})   // 🔥 Enable CORS in Spring Security
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            	    .requestMatchers("/auth/login", "/auth/register").permitAll()
            	    .requestMatchers("/uploads/**").permitAll()   // 🔥 FIX IMAGE 403
            	    .requestMatchers(HttpMethod.GET, "/All-customer")
            	    .hasAnyRole("ADMIN","USER")
            	    .requestMatchers("/customerSingle/**").permitAll()
            	    .requestMatchers(HttpMethod.POST, "/add-Customer")
            	    .hasAnyRole("ADMIN","USER")
            	    
            	    
            	    // car
            	    .requestMatchers("/All-cars").permitAll()
            	    .requestMatchers("/carSingle/**").permitAll()
            	    
            	     .requestMatchers(HttpMethod.GET, "/carSingle/**")
                    	.hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                    .requestMatchers(HttpMethod.POST, "/add-car")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                    .requestMatchers(HttpMethod.PUT, "/carUpdate/**")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                    .requestMatchers(HttpMethod.PUT, "/carStatus/**")
                        .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")

                        
                       // Rental
                       
                        .requestMatchers(HttpMethod.POST, "/add-rental")
                	    .hasAnyRole("ADMIN","USER")
                	    
                	    //booking
                	  
                        .requestMatchers(HttpMethod.GET,"/my-bookings")
                        .hasAnyRole("ADMIN","USER")

                       
            	    .anyRequest().authenticated()
            	)

            .addFilterBefore(jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

  
}
