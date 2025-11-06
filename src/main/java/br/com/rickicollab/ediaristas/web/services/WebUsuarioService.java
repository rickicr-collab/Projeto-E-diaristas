package br.com.rickicollab.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import br.com.rickicollab.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.rickicollab.ediaristas.core.exceptions.UsuarioJaCadastradoException;
import br.com.rickicollab.ediaristas.core.exceptions.UsuarioNaoEncontradoException;
import br.com.rickicollab.ediaristas.core.models.Usuario;
import br.com.rickicollab.ediaristas.core.repositories.UsuarioRepository;
import br.com.rickicollab.ediaristas.web.dto.UsuarioCadastroForm;
import br.com.rickicollab.ediaristas.web.dto.UsuarioEdicaoForm;
import br.com.rickicollab.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private WebUsuarioMapper mapper;

    public List<Usuario> buscarTodos() {
        var lista = repository.findAll();
        return lista;
    }

    public Usuario cadastrar(UsuarioCadastroForm form) {
        var senha = form.getSenha();
        var confirmarSenha = form.getConfirmacaoSenha();
        if (!senha.equals(confirmarSenha)) {
            var message = "os campos de senha não conferem !";
            var fieldError = new FieldError(form.getClass().getName(), "confirmacaoSenha", form.getConfirmacaoSenha(),
                    false, null, null, message);
            throw new SenhasNaoConferemException(message, fieldError);
        }

        var model = mapper.toModel(form);
        model.setTipoUsuario(TipoUsuario.ADMIN);
        validacaoCamposUnicos(model);
        var usuario = repository.save(model);
        return usuario;
    }

    public Usuario buscarPorId(long id) {
        var message = String.format("Usuario com ID %d não encontrado: ", id);
        var buscarId = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(message));
        return buscarId;

    }

    public UsuarioEdicaoForm buscarFormPorId(long id) {
        var usuario = buscarPorId(id);
        var usuarioForm = mapper.toForm(usuario);
        return usuarioForm;
    }

    public void excluirPorId(long id) {
        var usuarioBuscado = buscarPorId(id);
        repository.delete(usuarioBuscado);
    }

    public Usuario editar(UsuarioEdicaoForm form, long id) {
        var userBuscar = buscarPorId(id);
        var model = mapper.toModel(form);
        model.setId(userBuscar.getId());
        model.setSenha(userBuscar.getSenha());
        model.setTipoUsuario(userBuscar.getTipoUsuario());
        validacaoCamposUnicos(model);
        var userUpdate = repository.save(model);
        return userUpdate;
    }

    private void validacaoCamposUnicos(Usuario usuario) {      
        if (repository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
            var message = "O email já existe cadastrado a outro usuário!";
            var fieldError = new FieldError(usuario.getClass().getName(), "email", usuario.getEmail(), false, null, null, message);
            throw new UsuarioJaCadastradoException(message, fieldError);
        }
    }
}
