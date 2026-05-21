package br.com.rickicollab.ediaristas.api.mappers;

import java.util.stream.Stream;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.rickicollab.ediaristas.api.dtos.response.UsuarioCadastroResponse;
import br.com.rickicollab.ediaristas.api.dtos.response.UsuarioResponse;
import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import br.com.rickicollab.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiUsuarioMapper {

    ApiUsuarioMapper INSTANCE = Mappers.getMapper(ApiUsuarioMapper.class);

    @Mapping(target = "senha", source = "password")
    @Mapping(target = "fotoDocumento", ignore = true)
    @Mapping(target = "cidadesAtendidas", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "fotoUsuario", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reputacao", ignore = true)
    Usuario toModel(UsuarioRequest request);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    @Mapping(target = "links", ignore = true)
    UsuarioResponse toResponse(Usuario model);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario.id")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    @Mapping(target = "links", ignore = true)
    @Mapping(target = "token", ignore = true)
    UsuarioCadastroResponse toCadastroResponse(Usuario model);

    default TipoUsuario integerToTipoUsuario(Integer valor) {
        return Stream.of(TipoUsuario.values())
                .filter(tipoUsuario -> tipoUsuario.getId().equals(valor))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo Usuário inválido"));
    }

}
