package com.ale94.digital_banking_api.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ale94.digital_banking_api.domain.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // mapear datos del requeast a la clase de java
        String username = null;
        String password = null;
        try {
            // mapear datos del requeast a la clase de java
            UserEntity user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // usuario y contraseña que utilizaremos para autenticarnos
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        // AuthenticationManager es el objeto q se encarga de administrar la
        // autenticacion.
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // obtiene los detalles de usuario
        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername()); // generando token de acceso

        response.addHeader("Authorization", token); // enviar token x la cabecera de la respuesta

        Map<String, Object> httpResponse = new HashMap<>(); // cuerpo de la respuesta
        httpResponse.put("token", token);
        httpResponse.put("message", "Autenticación correcta");
        httpResponse.put("username", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse)); // respuesta
        response.setStatus(HttpStatus.OK.value()); // 200
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().flush(); // garantizar que todo se escriba correctamente

        super.successfulAuthentication(request, response, chain, authResult);
    }

}
