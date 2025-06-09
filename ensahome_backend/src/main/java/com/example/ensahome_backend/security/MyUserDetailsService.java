package com.example.ensahome_backend.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ensahome_backend.model.User;
import com.example.ensahome_backend.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new CustomUserDetails(
            user.getId(),                 // <-- ID réel
            user.getEmail(),             // username
            user.getPassword(),
            new ArrayList<>()            // ou authorities
        );
        }
}


// @Service
// public class MyUserDetailsService implements UserDetailsService {

//     @Autowired
//     private UserRepository userRepository; // ou ton repo MongoDB

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByEmail(username)
//                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         return new CustomUserDetails(
//             user.getId(),                 // <-- ID réel
//             user.getEmail(),             // username
//             user.getPassword(),
//             new ArrayList<>()            // ou authorities
//         );
//     }
// }
