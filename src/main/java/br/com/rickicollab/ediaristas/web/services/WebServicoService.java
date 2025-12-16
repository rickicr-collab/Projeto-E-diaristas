package br.com.rickicollab.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rickicollab.ediaristas.core.exceptions.ServicoNaoEncontradoException;
import br.com.rickicollab.ediaristas.core.models.Servico;
import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;
import br.com.rickicollab.ediaristas.web.dto.ServicoForm;
import br.com.rickicollab.ediaristas.web.mappers.WebServicoMapper;

@Service
public class WebServicoService {
    @Autowired
    private ServicoRepository repository;

    @Autowired
    private WebServicoMapper mapper;

    public List<Servico> buscarTodos(){
        var lista = repository.findAll();
        return lista;
    }


    @SuppressWarnings("null")
    public Servico cadastrar(ServicoForm form){
        var model = mapper.toModel(form);
        var servico = repository.save(model);
        return servico;
    }

    public Servico buscarPorId(long id){
        var buscar = repository.findById(id);
        if(buscar.isPresent()){
            return buscar.get();
        }
        var message = String.format("Servico com ID %d não encontrado", id);
        throw new ServicoNaoEncontradoException(message);
    }

    public Servico editar(ServicoForm form, long id){
        var servicoBuscado = buscarPorId(id);
        var model = mapper.toModel(form);
        model.setId(servicoBuscado.getId());
        var editado = repository.save(model);
        return editado;
    }


    @SuppressWarnings("null")
    public void excluirPorId(long id){
        var buscarId = buscarPorId(id);
        repository.delete(buscarId);
    }

}
