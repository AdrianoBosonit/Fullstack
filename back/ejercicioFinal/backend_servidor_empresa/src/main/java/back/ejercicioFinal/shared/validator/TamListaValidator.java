package back.ejercicioFinal.shared.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class TamListaValidator implements ConstraintValidator<LimitarLista, List> {

    private int size;

    @Override
    public void initialize(LimitarLista constraintAnnotation) {
        this.size=constraintAnnotation.tam();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        if (list.size() <= size) {
            return true;
        } else {
            return false;
        }
    }
}
