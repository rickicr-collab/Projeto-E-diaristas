package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.requests.EnderecoDiaristaRequest;
import br.com.rickicollab.ediaristas.api.dtos.response.EnderecoDiaristaResponse;
import br.com.rickicollab.ediaristas.core.models.EnderecoDiarista;

@Mapper(componentModel = "spring")
public interface ApiEnderecoDiaristaMapper {

    ApiEnderecoDiaristaMapper INSTANCE = Mappers.getMapper(ApiEnderecoDiaristaMapper.class);
     
    @Mapping(target = "id", ignore = true)
    EnderecoDiarista toModel(EnderecoDiaristaRequest request);

    EnderecoDiaristaResponse toResponse(EnderecoDiarista model);

}
