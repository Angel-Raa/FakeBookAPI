package com.github.angel.raa.modules.utils.jwt;

import com.github.angel.raa.modules.persistence.modesl.Token;
import com.github.angel.raa.modules.persistence.repository.TokenRepository;
import com.github.angel.raa.modules.persistence.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
@Log
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtTokenUtils jwtTokenUtils;
    /**
     * Realiza la lógica de filtrado para autenticar las solicitudes mediante tokens JWT.
     *
     * @param request     La solicitud HTTP entrante.
     * @param response    La respuesta HTTP saliente.
     * @param filterChain La cadena de filtros para invocar después de la autenticación.
     * @throws ServletException Si ocurre un error en la servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = jwtTokenUtils.extractJwtFromRequest(request);
        if(!StringUtils.hasLength(jwt)){
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Token> token = tokenRepository.findByToken(jwt);
        boolean isValid = validateToken(token);
        if(!isValid) filterChain.doFilter(request, response);
        String username = jwtTokenUtils.getUsernameFromToke(jwt);
        UserDetails details = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details,
                null, details.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);


    }


    /**
     * Validates the provided token.
     *
     * @param optionalToken An Optional object containing the token to be validated.
     * @return true if the token is valid and has not expired, false otherwise.
     * @throws IllegalArgumentException if the optionalToken parameter is null.
     */
    private boolean validateToken(@NotNull Optional<Token> optionalToken) throws IllegalArgumentException {
        if (optionalToken.isEmpty()) {
            log.info("Token does not exist");
            return false;
        }
        Token token = optionalToken.get();
        Date now = new Date(System.currentTimeMillis());
        return token.isValid() && token.getExpiration().after(now);
    }

}
