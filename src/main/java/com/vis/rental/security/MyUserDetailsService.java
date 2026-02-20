package com.vis.rental.security;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.vis.rental.entity.Role;
import com.vis.rental.entity.User;
import com.vis.rental.repository.UserRespository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRespository userRepository;
    
    

    @Override
    public UserDetails loadUserByUsername(String username)
            throws org.springframework.security.core.userdetails.UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new org.springframework.security.core.userdetails.UsernameNotFoundException(
                                "User not found"));

        // 🔥 Convert roles → authorities
        Set<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.getRolename())
                )
                .collect(java.util.stream.Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),   // MUST be BCrypt from DB
                authorities
        );
    }
}
