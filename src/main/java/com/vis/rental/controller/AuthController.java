package com.vis.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vis.rental.Model.AuthenticationRequest;
import com.vis.rental.Model.AuthenticationResponse;
import com.vis.rental.dto.AuthResponse;
import com.vis.rental.dto.RegisterRequest;
import com.vis.rental.entity.Role;
import com.vis.rental.entity.User;
import com.vis.rental.repository.RoleRepository;
import com.vis.rental.repository.UserRespository;
import com.vis.rental.security.JwtUtil;
import com.vis.rental.security.MyUserDetailsService;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRespository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {

        System.out.println("LOGIN CONTROLLER HIT");

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );

        UserDetails userDetails =
            userDetailsService.loadUserByUsername(request.getUsername());

        System.out.println("BEFORE TOKEN GENERATION");

        try {
            String token = jwtUtil.generateToken(userDetails);
            System.out.println("AFTER TOKEN GENERATION");
            System.out.println("JWT TOKEN = " + token);
            return new AuthenticationResponse(token);
        } catch (Exception e) {
            System.out.println("JWT ERROR =====================");
            e.printStackTrace(); // 👈 THIS IS CRITICAL
            throw e;
        }
    }
    
    
  
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new AuthResponse("Username already exists"));
        }

        // 1️⃣ Create user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 2️⃣ Assign USER role
        Role userRole = roleRepository.findByRolename("USER")
                .orElseThrow(() -> new RuntimeException("ROLE USER not found"));

        user.getRoles().add(userRole); // ✅ safe now

        // 3️⃣ Save user
        userRepository.save(user);

        // 4️⃣ Auto-login
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        // 5️⃣ Return token
        return ResponseEntity.ok(new AuthResponse(token));
    }


   

}