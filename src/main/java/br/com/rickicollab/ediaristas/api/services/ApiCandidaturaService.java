package br.com.rickicollab.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.api.dtos.response.MensagemResponse;
import br.com.rickicollab.ediaristas.core.exceptions.DiariaNaoEncontradaException;
import br.com.rickicollab.ediaristas.core.models.Diaria;
import br.com.rickicollab.ediaristas.core.repositories.DiariaRepository;
import br.com.rickicollab.ediaristas.core.utils.SecurityUtils;
import br.com.rickicollab.ediaristas.core.validators.CandidaturaValidator;

@Service
public class ApiCandidaturaService {

    @Autowired
    private DiariaRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private CandidaturaValidator validator;

    public MensagemResponse candidatar(Long id) {
        var diaria = buscarDiariaPorId(id);
        validator.validar(diaria);
        var usuarioLogado = securityUtils.getUsuarioLogado();
        diaria.getCandidatos().add(usuarioLogado);
        repository.save(diaria);
        return new MensagemResponse("Candidatura realizada com sucesso!");
    }

    private Diaria buscarDiariaPorId(Long id) {
        var mensagem = String.format("Diária com id %d não encontrada", id);
        return repository.findById(id)
            .orElseThrow(() -> new DiariaNaoEncontradaException(mensagem));
    }
}

