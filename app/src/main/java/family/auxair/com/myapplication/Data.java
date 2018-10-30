package family.auxair.com.myapplication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

@interface Data {
    String value() default "";
}
