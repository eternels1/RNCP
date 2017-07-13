package exercice_java_8_ReflexionBean.Annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface BooleanConverter {
	String trueString() default "true";
	String falseString() default "false";	

}
