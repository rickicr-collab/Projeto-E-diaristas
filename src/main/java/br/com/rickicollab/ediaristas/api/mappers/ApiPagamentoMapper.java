package br.com.rickicollab.ediaristas.api.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.api.dtos.response.PagamentoResponse;
import br.com.rickicollab.ediaristas.core.enums.DiariaStatus;
import br.com.rickicollab.ediaristas.core.models.Pagamento;

@Mapper(componentModel = "spring")
public interface ApiPagamentoMapper {

    ApiPagamentoMapper INSTANCE = Mappers.getMapper(ApiPagamentoMapper.class);

    @Mapping(target = "status", source = "diaria.status")
    @Mapping(target = "valor", source = "diaria.preco")
    @Mapping(target = "valorDeposito", source = "model")
    @Mapping(target = "createdAt", ignore = true)
    PagamentoResponse toResponse(Pagamento model);

    default BigDecimal mapValorDeposito(Pagamento model) {
        var precoDiaria = model.getDiaria().getPreco();
        var comissao = model.getDiaria().getValorComissao();

        return precoDiaria.subtract(comissao);
    }

    default Integer diariaStatusToInteger(DiariaStatus status) {
        return status.equals(DiariaStatus.TRANSFERIDO) ? 1 : 2;
    }

}
