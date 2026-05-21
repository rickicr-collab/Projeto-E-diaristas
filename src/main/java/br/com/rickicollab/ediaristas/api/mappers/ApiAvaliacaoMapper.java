package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.requests.AvaliacaoRequest;
import br.com.rickicollab.ediaristas.api.dtos.response.AvaliacaoResponse;
import br.com.rickicollab.ediaristas.core.models.Avaliacao;

@Mapper(componentModel = "spring")
public interface ApiAvaliacaoMapper {

    ApiAvaliacaoMapper INSTANCE = Mappers.getMapper(ApiAvaliacaoMapper.class);

    @Mapping(target = "avaliado", ignore = true)
    @Mapping(target = "avaliador", ignore = true)
    @Mapping(target = "diaria", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visibilidade", ignore = true)
    Avaliacao toModel(AvaliacaoRequest request);

    @Mapping(target = "nomeAvaliador", source = "avaliador.nomeCompleto")
    @Mapping(target = "fotoAvaliador", source = "avaliador.fotoUsuario.url")
    AvaliacaoResponse toResponse(Avaliacao model);

}
