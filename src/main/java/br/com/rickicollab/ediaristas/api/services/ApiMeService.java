package br.com.rickicollab.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.api.dtos.response.UsuarioResponse;
import br.com.rickicollab.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.rickicollab.ediaristas.core.utils.SecurityUtils;

@Service
public class ApiMeService {

    @Autowired
    private ApiUsuarioMapper usuarioMapper;

    @Autowired
    private SecurityUtils securityUtils;

    public UsuarioResponse obterUsuarioLogado() {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        return usuarioMapper.toResponse(usuarioLogado);
    }

}
