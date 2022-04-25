package back.ejercicioFinal.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = CheckValoresValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface CheckValores {
    String message() default "CheckValores";
    String parametros();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
