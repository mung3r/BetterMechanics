package net.edoxile.configparser;

import net.edoxile.configparser.annotations.Int;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class ConfigLoader {
    public static void loadConfig(Object configableObject){
        Field[] fields = configableObject.getClass().getDeclaredFields();
        for(int i = 0; i<fields.length; i++){
            Annotation annotation = fields[i].getAnnotation(Int.class);
            //TODO: check annotation existing, call field/name on YAML.get...();
        }
    }
}
