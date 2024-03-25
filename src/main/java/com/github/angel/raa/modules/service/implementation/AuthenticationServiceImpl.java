package com.github.angel.raa.modules.service.implementation;

import com.github.angel.raa.modules.exception.UserNotFoundExceptionHandler;
import com.github.angel.raa.modules.persistence.modesl.Role;
import com.github.angel.raa.modules.persistence.modesl.Token;
import com.github.angel.raa.modules.persistence.modesl.Users;
import com.github.angel.raa.modules.persistence.repository.TokenRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import com.github.angel.raa.modules.service.intefaces.AuthenticationService;
import com.github.angel.raa.modules.utils.DTO.Login;
import com.github.angel.raa.modules.utils.DTO.Sign;
import com.github.angel.raa.modules.utils.api.AuthenticationResponse;
import com.github.angel.raa.modules.utils.api.Message;
import com.github.angel.raa.modules.utils.jwt.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final JwtTokenUtils utils;
    @Override
    public AuthenticationResponse login(@NotNull Login login) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                login.username(), login.password()
        );
        authenticationManager.authenticate(authentication);
        Users users = userRepository.findByUsername(login.username())
                .orElseThrow(() -> new UserNotFoundExceptionHandler(Message.USER_NOT_FOUND_USERNAME,404));
        String jwt = utils.generateToken(users);
        saverToken(users, jwt);
        return AuthenticationResponse.builder()
                .message(Message.LOGIN_SUCCESSFULLY)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponse register(@NotNull Sign sign) {
        String jwt = createAndSaveToken(sign);
        return AuthenticationResponse.builder()
                .message(Message.SIGN_SUCCESSFULLY)
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .jwt(jwt)
                .build();
    }


    @Override
    public boolean isTokenValid(String token) {
        try {
            utils.getUsernameFromToke(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
        }

    @Override
    public AuthenticationResponse refreshToken(String token) {
        return null;
    }

    @Override
    public AuthenticationResponse logout(HttpServletRequest request) {
        return null;
    }
    /**
     * Crea un token JWT y guarda la información del usuario y el token en la base de datos.
     *
     * @param sign El objeto Sign que contiene los datos para crear el token y guardar la información del usuario y el token.
     * @return El token JWT generado.
     */
    private String createAndSaveToken(@NotNull Sign sign) {
        Users users = new Users();
        Token token = new Token();

        users.setUsername(sign.username());
        users.setPassword(bCryptPasswordEncoder.encode(sign.password()));
        users.setEmail(sign.email());
        users.setRole(Role.ROLE_USER);
        String jwt = utils.generateToken(users);
        token.setToken(jwt);
        token.setExpiration(utils.getExpirationFromToken(jwt));
        token.setValid(true);
        token.setUser(users);

        userRepository.save(users);
        tokenRepository.save(token);
        return jwt;
    }

    private void saverToken(Users users, String jwt) {
        Token token = new Token();
        token.setToken(jwt);
        token.setUser(users);
        token.setValid(true);
        token.setExpiration(utils.getExpirationFromToken(jwt));
        tokenRepository.save(token);
    }

}
