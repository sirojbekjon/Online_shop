package io.getarrays.start_up.service;

import io.getarrays.start_up.entity.User;
import io.getarrays.start_up.payload.LoginDto;
import io.getarrays.start_up.payload.Userdto;
import io.getarrays.start_up.repository.RoleRepository;
import io.getarrays.start_up.repository.UserRepository;
import io.getarrays.start_up.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public HttpEntity<?> saveUser(Userdto userdto) {
        User user = new User(
                userdto.getUsername(),
                passwordEncoder.encode(userdto.getPassword()),
                userdto.getPhoneNumber(),
                roleRepository.findByName("USER"),
                true
        );
        User savedUser = userRepository.save(user);


//        boolean sendEmail = emailService.sendEmail(user.getEmail(), user.getEmailCode());
        return ResponseEntity.status(202).body(savedUser);
    }

//    public HttpEntity<?> verifyEmail(String emailCode, String email) {
//        Optional<User> optionalUser = userRepository.findByEmailCode(emailCode);
//        if (optionalUser.isPresent()){
//            User user = optionalUser.get();
//            user.setEnabled(true);
//            user.setEmailCode(null);
//            userRepository.save(user);
//            return ResponseEntity.status(202).body("Email has been active");
//        }else {
//            return ResponseEntity.status(404).body("User Not Found");
//        }
//    }

    public HttpEntity<?> login(LoginDto loginDto) {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
             User user =(User)authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getRole());
            return ResponseEntity.status(202).body(token);
        }catch (BadCredentialsException badCredentialsException){
                    return ResponseEntity.status(404).body("Parol yoki login notug'ri");
        }
    }
}
