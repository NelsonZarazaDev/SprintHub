package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.dto.AuthResponse;
import com.SprintHub.scrum_project_manager.dto.LoginRequest;
import com.SprintHub.scrum_project_manager.exception.AlreadyExistsException;
import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.repository.UsersRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(@Valid Users request) {
        Optional<Users> existingUserByEmail = usersRepository.getUserByEmail(request.getEmailUser());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(Constants.USER_EMAIL_EXISTS.getMessage());
        }

        Users users = Users.builder()
                .firstnameUser(request.getFirstnameUser())
                .lastNameUser(request.getLastNameUser())
                .dateBirthUser(request.getDateBirthUser())
                .emailUser(request.getEmailUser())
                .passwordUser(passwordEncoder.encode(request.getPassword()))
                .tokenUser(request.getTokenUser())
                .build();

        usersRepository.save(users);
        return AuthResponse.builder().token(jwtService.getToken(users)).build();
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationServiceException(Constants.CREDENTIAL_INVALID.getMessage());
        }
        Users users = usersRepository.getUserByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(Constants.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(users);
        return AuthResponse.builder().token(token).build();
    }
}