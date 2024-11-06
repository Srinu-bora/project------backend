// // // package com.example.credentials.controller;

// // // import com.example.credentials.dto.UserDto;
// // // import com.example.credentials.service.UserService;
// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.web.bind.annotation.*;

// // // @RestController
// // // @RequestMapping("/api/users")
// // // public class UserController {

// // //     private final UserService userService;

// // //     public UserController(UserService userService) {
// // //         this.userService = userService;
// // //     }

// // //     @PostMapping("/register")
// // //     public ResponseEntity<String> registerUser(@RequestBody UserDto request) {
// // //         userService.registerUser(request.getUsername(), request.getPassword());
// // //         return ResponseEntity.ok("User registered successfully!");
// // //     }
// // // }
// // package com.example.credentials.controller;

// // import com.example.credentials.dto.UserDto;
// // import com.example.credentials.model.User;
// // import com.example.credentials.service.UserService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.Optional;

// // @RestController
// // @RequestMapping("/api/users")
// // public class UserController {

// //     @Autowired
// //     private UserService userService;

// //     @PostMapping("/register")
// //     public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
// //         return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
// //     }

// //     @PostMapping("/login")
// //     public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
// //         Optional<User> user = userService.findByUsername(userDto.getUsername());
// //         if (user.isPresent() && user.get().getPassword().equals(userDto.getPassword())) {
// //             // For simplicity, we're not handling JWT or sessions here; add your authentication logic.
// //             return ResponseEntity.ok("User logged in successfully!");
// //         }
// //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
// //     }
// // }
// package com.example.credentials.controller;

// import com.example.credentials.dto.UserDto;
// import com.example.credentials.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;

// @RestController
// @RequestMapping("/api/users")
// @CrossOrigin(origins = "http://localhost:3000")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
//         try {
//         String response = userService.registerUser(userDto);
//         return new ResponseEntity<>(response, HttpStatus.CREATED);
//     } catch (ResponseStatusException e) {
//         return ResponseEntity.status(e.getStatus()).body(e.getReason());
//     }
//     }

//     @PostMapping("/login")
//     public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
//         String loginResponse = userService.loginUser(userDto);
//         if (loginResponse.equals("User logged in successfully!")) {
//             return ResponseEntity.ok(loginResponse);
//         }
//         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
//     }
// }
package com.example.credentials.controller;

import com.example.credentials.dto.UserDto;
import com.example.credentials.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            String response = userService.registerUser(userDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        try {
            String loginResponse = userService.loginUser(userDto);
            return ResponseEntity.ok(loginResponse);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
