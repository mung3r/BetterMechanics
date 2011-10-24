package net.edoxile.configparser.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigEntityNode {
    String value();
}
