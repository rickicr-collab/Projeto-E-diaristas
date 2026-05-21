package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.response.UsuarioDiariaResponse;
import br.com.rickicollab.ediaristas.core.enums.TipoUsuario;
import br.com.rickicollab.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiUsuarioDiariaMapper {

    ApiUsuarioDiariaMapper INSTANCE = Mappers.getMapper(ApiUsuarioDiariaMapper.class);

    @Mapping(target = "tipoUsuario", source = "tipoUsuario")
    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    UsuarioDiariaResponse toResponse(Usuario model);


    default Integer map(TipoUsuario tipoUsuario){
        return tipoUsuario.getId();
    }

}   
