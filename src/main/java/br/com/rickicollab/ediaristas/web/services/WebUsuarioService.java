package br.com.rickicollab.ediaristas.web.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import br.com.rickicollab.ediaristas.core.exceptions.SenhaIncorretaException;
import br.com.rickicollab.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.rickicollab.ediaristas.core.exceptions.UsuarioJaCadastradoException;
import br.com.rickicollab.ediaristas.core.exceptions.UsuarioNaoEncontradoException;
import br.com.rickicollab.ediaristas.core.models.Usuario;
import br.com.rickicollab.ediaristas.core.repositories.UsuarioRepository;
import br.com.rickicollab.ediaristas.web.dto.AlterarSenhaForm;
import br.com.rickicollab.ediaristas.web.dto.UsuarioCadastroForm;
import br.com.rickicollab.ediaristas.web.dto.UsuarioEdicaoForm;
import br.com.rickicollab.ediaristas.web.interfaces.IconfirmacaoSenha;
import br.com.rickicollab.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    private final UsuarioRepository repository;
    private final WebUsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public WebUsuarioService(UsuarioRepository repository, WebUsuarioMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> buscarTodos() {
        return repository.findAll();

    }

    public Usuario cadastrar(UsuarioCadastroForm form) {
        validarConfirmacaoSenhas(form);

        var model = mapper.toModel(form);
        var senhaHash = passwordEncoder.encode(model.getSenha());
        model.setSenha(senhaHash);
        model.setTipoUsuario(TipoUsuario.ADMIN);
        validacaoCamposUnicos(model);
        var usuario = repository.save(model);
        return usuario;
    }

    public Usuario buscarPorId(long id) {
        var message = String.format("Usuario com ID %d não encontrado.", id);
        var buscarId = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(message));
        return buscarId;

    }

    public Usuario buscarPorEmail(String email) {
        var message = String.format("Usuario com email %s não encontrado.", email);
        var buscarEmail = repository.findByEmail(email).orElseThrow(() -> new UsuarioNaoEncontradoException(message));
        return buscarEmail;
    }

    public UsuarioEdicaoForm buscarFormPorId(long id) {
        var usuario = buscarPorId(id);
        var usuarioForm = mapper.toForm(usuario);
        return usuarioForm;
    }

    @SuppressWarnings("null")
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

    public void alterarSenha(AlterarSenhaForm form, String email) {
        var usuario = buscarPorEmail(email);

        validarConfirmacaoSenhas(form);

        var senhaAtual = usuario.getSenha();
        var senhaAntiga = form.getSenhaAntiga();
        var senha = form.getSenha();

        if (!passwordEncoder.matches(senhaAntiga, senhaAtual)) {
            var message = "A senha antiga está incorreta!";
            var fieldError = new FieldError(form.getClass().getName(), "senhaAntiga", senhaAntiga,
                    false, null, null, message);
            throw new SenhaIncorretaException(message, fieldError);
        }

        var novaSenhaHash = passwordEncoder.encode(senha);
        usuario.setSenha(novaSenhaHash);
        repository.save(usuario);

    }

    private void validacaoCamposUnicos(Usuario usuario) {
        if (repository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
            var message = "O email já existe cadastrado a outro usuário!";
            @SuppressWarnings("null")
            var fieldError = new FieldError(usuario.getClass().getName(), "email", usuario.getEmail(), false, null,
                    null, message);
            throw new UsuarioJaCadastradoException(message, fieldError);
        }
    }

    private void validarConfirmacaoSenhas(IconfirmacaoSenha obj) {
        var senha = obj.getSenha();
        var confirmacaoSenha = obj.getConfirmacaoSenha();
        if (!senha.equals(confirmacaoSenha)) {
            var message = "os campos de senha não conferem!";
            @SuppressWarnings("null")
            var fieldError = new FieldError(obj.getClass().getName(), "confirmacaoSenha", obj.getConfirmacaoSenha(),
                    false, null, null, message);
            throw new SenhasNaoConferemException(message, fieldError);
        }

    }
}
