package back.ejercicioFinal.shared.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CheckValoresValidator implements ConstraintValidator<CheckValores, Object> {

    String parametros;

    @Override
    public void initialize(CheckValores constraintAnnotation) {
        this.parametros = constraintAnnotation.parametros();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> set = new HashSet<String>(Arrays.asList(parametros.replace(" ","").split("&&")));
        if (set.contains(object.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
