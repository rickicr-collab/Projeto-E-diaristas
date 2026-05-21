package br.com.rickicollab.ediaristas.api.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.response.ServicoResponse;
import br.com.rickicollab.ediaristas.core.models.Servico;

@Mapper(componentModel = "spring")
public interface ApiServicoMapper {

    ApiServicoMapper INSTANCE = Mappers.getMapper(ApiServicoMapper.class);

    @Mapping(target = "icone", source = "icone.nome")
    @Mapping(target = "porcentagemComissao", ignore = true)
    ServicoResponse toResponse(Servico model);

}
