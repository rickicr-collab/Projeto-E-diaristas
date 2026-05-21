package br.com.rickicollab.ediaristas.core.validators;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ServicoExistsByIdValidator implements ConstraintValidator<ServicoExistsById, Long> {
    
    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return servicoRepository.existsById(value);
    }
}
