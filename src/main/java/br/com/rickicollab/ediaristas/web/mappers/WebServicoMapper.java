package br.com.rickicollab.ediaristas.web.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.rickicollab.ediaristas.core.models.Servico;
import br.com.rickicollab.ediaristas.web.dto.ServicoForm;

@Mapper(componentModel="spring")
public interface WebServicoMapper {

    WebServicoMapper INSTANCE = Mappers.getMapper(WebServicoMapper.class);
    
     
     Servico toModel(ServicoForm form);

     ServicoForm toForm(Servico model);

}
