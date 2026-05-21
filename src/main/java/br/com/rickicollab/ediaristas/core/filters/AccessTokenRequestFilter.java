package br.com.rickicollab.ediaristas.core.filters;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.rickicollab.ediaristas.api.dtos.response.ErrorResponse;
import br.com.rickicollab.ediaristas.core.services.token.adapters.TokenService;
import br.com.rickicollab.ediaristas.core.services.token.exceptions.TokenServiceException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AccessTokenRequestFilter extends OncePerRequestFilter {

    private final static String TOKEN_TYPE = "Bearer ";

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            tryDoFilterInternal(request, response, filterChain);
        } catch (TokenServiceException exception) {
            var status = HttpStatus.UNAUTHORIZED;
            var errorResponse = ErrorResponse.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .mensagem(exception.getLocalizedMessage())
                .path(request.getRequestURI())
                .build();

            var json = objectMapper.writeValueAsString(errorResponse);

            response.setStatus(status.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(json);
        }
    }

    private void tryDoFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = null;
        String email = null;

        var authorizationHeader = request.getHeader("Authorization");

        if (isTokenPresente(authorizationHeader)) {
            token = authorizationHeader.substring(TOKEN_TYPE.length());
            email = tokenService.getSubjectFromAccessToken(token);
        }

        if (isEmailNotInContext(email)) {
            addEmailInContext(request, email);
        }

        filterChain.doFilter(request, response);
    }

    private Boolean isTokenPresente(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith(TOKEN_TYPE);
    }

    private Boolean isEmailNotInContext(String email) {
        return email != null && !email.isEmpty()
        && SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private void addEmailInContext(HttpServletRequest request, String email) {
        var usuario = userDetailsService.loadUserByUsername(email);

        var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

}
