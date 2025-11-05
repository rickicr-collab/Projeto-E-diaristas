package br.com.rickicollab.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.core.models.Usuario;
import br.com.rickicollab.ediaristas.web.dto.UsuarioCadastroForm;
import br.com.rickicollab.ediaristas.web.dto.UsuarioEdicaoForm;


@Mapper(componentModel = "spring")
public interface WebUsuarioMapper {

    WebUsuarioMapper INSTANCE = Mappers.getMapper(WebUsuarioMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toModel(UsuarioCadastroForm form);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "tipoUsuario", ignore = true)
    Usuario toModel(UsuarioEdicaoForm form);

    UsuarioEdicaoForm toForm(Usuario model);

}
