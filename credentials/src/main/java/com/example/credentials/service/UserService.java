// package com.example.credentials.service;

// import com.example.credentials.dto.UserDto;
// import com.example.credentials.model.User;
// import com.example.credentials.repository.UserRepository;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public String registerUser(UserDto userDto) {
//         User user = new User();
//         user.setUsername(userDto.getUsername());
//         user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//         userRepository.save(user);
//         return "User registered successfully!";
//     }

//     public String loginUser(UserDto userDto) {
//         Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
//         if (userOptional.isPresent() && passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
//             // User is authenticated, proceed with your login logic (e.g., generate a token)
//             return "User logged in successfully!";
//         }
//         return "Invalid username or password";
//     }
// }



package com.example.credentials.service;

import com.example.credentials.dto.UserDto;
import com.example.credentials.model.User;
import com.example.credentials.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(UserDto userDto) {
        // Check if the username already exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        // Check if the user exists and if the password matches
        if (userOptional.isPresent() && passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
            // User is authenticated, proceed with your login logic (e.g., generate a token)
            return "User logged in successfully!";
        }
        
        // Return message for invalid credentials
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
    }
}
