package br.com.rickicollab.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.api.dtos.requests.RefrehRequest;
import br.com.rickicollab.ediaristas.api.dtos.requests.TokenRequest;
import br.com.rickicollab.ediaristas.api.dtos.response.TokenResponse;
import br.com.rickicollab.ediaristas.core.services.TokenBlackListService;
import br.com.rickicollab.ediaristas.core.services.token.adapters.TokenService;

@Service
public class ApiAuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    public TokenResponse autenticar(TokenRequest tokenRequest) {
        var email = tokenRequest.getEmail();
        var senha = tokenRequest.getSenha();

        var autenticacao = new UsernamePasswordAuthenticationToken(email, senha);
        authenticationManager.authenticate(autenticacao);

        var access = tokenService.generateAccessToken(email);
        var refresh = tokenService.generateRefreshToken(email);

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reautenticar(RefrehRequest refrehRequest) {
        var token = refrehRequest.getRefresh();
        tokenBlackListService.verificarToken(token);

        var email = tokenService.getSubjectFromRefreshToken(token);

        userDetailsService.loadUserByUsername(email);

        var access = tokenService.generateAccessToken(email);
        var refresh = tokenService.generateRefreshToken(email);

        tokenBlackListService.colocarTokenNaBlackList(token);

        return new TokenResponse(access, refresh);
    }

    public void logout(RefrehRequest refrehRequest) {
        var token = refrehRequest.getRefresh();
        tokenBlackListService.colocarTokenNaBlackList(token);
    }

}
