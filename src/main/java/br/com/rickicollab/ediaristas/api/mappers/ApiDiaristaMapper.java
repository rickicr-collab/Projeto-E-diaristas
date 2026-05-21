package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.response.DiaristaLocalidadeResponse;
import br.com.rickicollab.ediaristas.core.models.Usuario;

@Mapper(componentModel = "spring")
public interface ApiDiaristaMapper {

    ApiDiaristaMapper INSTACE = Mappers.getMapper(ApiDiaristaMapper.class);

    @Mapping(target = "fotoUsuario", source = "fotoUsuario.url")
    @Mapping(target = "cidade", ignore = true)
    
    DiaristaLocalidadeResponse toDiaristaLocalidadeResponse(Usuario model);
    
}
