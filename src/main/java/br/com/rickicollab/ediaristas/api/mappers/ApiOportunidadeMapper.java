package br.com.rickicollab.ediaristas.api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.rickicollab.ediaristas.api.dtos.response.AvaliacaoResponse;
import br.com.rickicollab.ediaristas.api.dtos.response.OportunidadeResponse;
import br.com.rickicollab.ediaristas.core.models.Diaria;
import br.com.rickicollab.ediaristas.core.models.Servico;
import br.com.rickicollab.ediaristas.core.repositories.AvaliacaoRepository;
import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;

@Mapper(componentModel = "spring", uses = ApiUsuarioDiariaMapper.class)
public abstract class ApiOportunidadeMapper {

    @Autowired
    protected ServicoRepository servicoRepository;

    @Autowired
    protected AvaliacaoRepository avaliacaoRepository;

    @Autowired
    protected ApiAvaliacaoMapper avaliacaoMapper;

    public static final ApiOportunidadeMapper INSTANCE = Mappers.getMapper(ApiOportunidadeMapper.class);

    @Mapping(target = "status", source = "status.id")
    @Mapping(target = "nomeServico", source = "servico.nome")
    @Mapping(target = "servico", source = "servico.id")
    @Mapping(target = "avaliacoesCliente", source = "model")
    @Mapping(target = "links", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract OportunidadeResponse toResponse(Diaria model);

    protected List<AvaliacaoResponse> mapAvaliacoesCliente(Diaria model) {
        var cliente = model.getCliente();
        return avaliacaoRepository.getUltimasAvaliacoes(cliente)
            .stream()
            .map(avaliacaoMapper::toResponse)
            .toList();
    }

    protected Servico longToServico(Long servicoId) {
        return servicoRepository.findById(servicoId)
            .orElseThrow(IllegalArgumentException::new);
    }


}
