package br.com.rickicollab.ediaristas.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rickicollab.ediaristas.api.dtos.requests.DiariaRequest;
import br.com.rickicollab.ediaristas.api.dtos.response.DiariaResponse;
import br.com.rickicollab.ediaristas.core.models.Diaria;
import br.com.rickicollab.ediaristas.core.models.Servico;
import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;

@Mapper(componentModel = "spring", uses = ApiUsuarioDiariaMapper.class)
public abstract class ApiDiariaMapper {

    @Autowired
    protected ServicoRepository servicoRepository;

    public static final ApiDiariaMapper INSTANCE = Mappers.getMapper(ApiDiariaMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "candidatos", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "diarista", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "motivoCancelamento", ignore = true)
    @Mapping(target = "pagamentos", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "valorComissao", ignore = true)
    public abstract Diaria toModel(DiariaRequest request);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "nomeServico", source = "servico.nome")
    @Mapping(target = "servico", source = "servico.id")
    @Mapping(target = "links", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract DiariaResponse toResponse(Diaria model);

    protected Servico longToServico(Long servicoId) {
        return servicoRepository.findById(servicoId)
            .orElseThrow(IllegalArgumentException::new);
    }

}
