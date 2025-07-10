package com.desafios.galeriaimagensspring.infrastructure.security.filters;

import com.desafios.galeriaimagensspring.core.gateways.UserGateway;
import com.desafios.galeriaimagensspring.core.model.User;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.UserDetailsImpl;
import com.desafios.galeriaimagensspring.infrastructure.security.config.SecurityConfiguration;
import com.desafios.galeriaimagensspring.infrastructure.security.autentication.JwtTokenService;
import com.desafios.galeriaimagensspring.interfaces.mapper.UserMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final UserGateway userGateway;

    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (checkIfEndpointIsNotPublic(request)){
            String token = recoveryToken(request);

            if (token != null){
                String subject = jwtTokenService.getSubjectFromToken(token);
                User user = userGateway.findByEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(UserMapper.toEntity(user));

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                throw new RuntimeException("token is missing");
            }
        }
        filterChain.doFilter(request, response);

    }

    private String recoveryToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            return token.replace("Bearer ", "").trim();
        }else {
            throw new RuntimeException("Malformed JWT token");
        }

//        throw new RuntimeException("Authorization header is missing or invalid");
//        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
