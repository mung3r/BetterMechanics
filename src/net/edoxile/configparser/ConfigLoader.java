package net.edoxile.configparser;

import net.edoxile.configparser.annotations.*;
import org.bukkit.util.config.Configuration;
import sun.plugin.dom.exception.PluginNotSupportedException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class ConfigLoader {
    private static HashMap<String, Configuration> pluginYamlMap = new HashMap<String, Configuration>();
    private static final Logger log = Logger.getLogger("Minecraft");

    public static void loadPluginYaml(String pluginName, Configuration pluginConfig) {
        pluginYamlMap.put(pluginName, pluginConfig);
    }

    public static void loadConfig(String pluginName, Object configableObject) throws PluginNotSupportedException {
        if (configableObject.getClass().getAnnotation(ConfigEntity.class) == null)
            return;
        Configuration config = pluginYamlMap.get(pluginName);
        if (config == null) {
            throw new PluginNotSupportedException(pluginName);
        }

        Field[] fields = configableObject.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Type fieldType = fields[i].getAnnotation(Type.class);
            if (fieldType != null) {
                boolean isList = fields[i].getAnnotation(NodeList.class) != null;
                Class<?> fieldClass = fieldType.value();
                String fieldName = fields[i].getName();
                try {
                    Method getter = Configuration.class.getMethod("get" + fieldClass + (isList ? "List" : ""), String.class, (isList ? java.util.List.class : fieldClass));
                    Object data = getter.invoke(config, configableObject.getClass().toString() + "." + fieldName, null);
                    Method setter = configableObject.getClass().getMethod("set" + fieldName, fieldClass);
                    setter.invoke(configableObject, data);
                } catch (NoSuchMethodException e) {
                    log.severe("[ConfigParser] Method not found: " + e.getMessage());
                } catch (InvocationTargetException e) {
                    log.severe("[ConfigParser] InvocationTarget is invald: " + e.getMessage());
                } catch (IllegalAccessException e) {
                    log.severe("[ConfigParser] Method is not public: " + e.getMessage());
                }
            }
        }
    }
}
