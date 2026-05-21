package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.response.CidadeAtendidaResponse;
import br.com.rickicollab.ediaristas.core.models.CidadeAtendida;

@Mapper(componentModel = "spring")
public interface ApiCidadeAtendidaMapper {

    ApiCidadeAtendidaMapper INSTANCE = Mappers.getMapper(ApiCidadeAtendidaMapper.class);

    CidadeAtendidaResponse toResponse(CidadeAtendida model);

}
