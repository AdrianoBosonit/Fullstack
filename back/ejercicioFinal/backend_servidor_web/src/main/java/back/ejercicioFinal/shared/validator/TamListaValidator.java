package back.ejercicioFinal.shared.validator;

import back.ejercicioFinal.content.Reserva.ReservaEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TamListaValidator implements ConstraintValidator<LimitarLista, ReservaEntity> {

    private int size;

    @Override
    public void initialize(LimitarLista constraintAnnotation) {
        this.size=constraintAnnotation.tam();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ReservaEntity list, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println( "   "+size);
        return true;
    }


}
