package com.example.Security_Prac.service;

import com.example.Security_Prac.dto.request.UserRequestDto;
import com.example.Security_Prac.dto.response.ResponseDto;
import com.example.Security_Prac.exception.AllException;
import com.example.Security_Prac.exception.AlreadyRegisteredUserException;
import com.example.Security_Prac.model.User;
import com.example.Security_Prac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 주입
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new AlreadyRegisteredUserException();
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_USER")
                .build();
        return userRepository.save(user);
    }


    public User signupAdmin(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new AlreadyRegisteredUserException();
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authority("ROLE_ADMIN")
                .build();
        return userRepository.save(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
