package br.com.rickicollab.ediaristas.core.services.gatewaypagamento.adapters;

import br.com.rickicollab.ediaristas.core.models.Diaria;
import br.com.rickicollab.ediaristas.core.models.Pagamento;

public interface GatewayPagamentoService {

    Pagamento pagar(Diaria diaria, String cardHash);

    Pagamento realizarEstornoTotal(Diaria diaria);

    Pagamento realizarEstornoParcial(Diaria diaria);

}
