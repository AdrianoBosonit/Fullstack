package back.ejercicioFinal.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TamListaValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE,PARAMETER})
@Retention(RUNTIME)
public @interface LimitarLista {
    String message() default "TamInvalido";
    int tam() default 100;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}