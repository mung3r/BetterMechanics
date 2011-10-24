package net.edoxile.configparser;

import net.edoxile.configparser.annotations.*;
import org.bukkit.util.config.Configuration;
import sun.plugin.dom.exception.PluginNotSupportedException;

import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class ConfigLoader {
    private enum Classes{
        Integer,
        Boolean,
        String,
        Object,
        Double;

        private static HashMap<String, Classes> classesMap = new HashMap<String, Classes>();

        static{
            for(Classes classes : Classes.values()){
                classesMap.put(classes.name(), classes);
            }
        }

        public static Classes fromName(String name){
            return classesMap.get(name);
        }
    }

    private static HashMap<String, Configuration> pluginYamlMap = new HashMap<String, Configuration>();
    private static final Logger log = Logger.getLogger("Minecraft");

    public static void loadPluginYaml(String pluginName, Configuration pluginConfig) {
        pluginYamlMap.put(pluginName, pluginConfig);
    }

    public static void loadConfig(String pluginName, Object configableObject) throws PluginNotSupportedException,IllegalAccessException {
        Configuration config = pluginYamlMap.get(pluginName);
        if (config == null) {
            throw new PluginNotSupportedException(pluginName);
        }

        String objectPrefix,nodeSuffix;
        ConfigEntityNode configEntityNode = configableObject.getClass().getAnnotation(ConfigEntityNode.class);
        if (configEntityNode == null)
            return;
        objectPrefix = configEntityNode.value();

        Field[] fields = configableObject.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            NodeType fieldType = fields[i].getAnnotation(NodeType.class);
            if (fieldType != null) {
                nodeSuffix = fieldType.node();

                //Have to test this...
                boolean isList = fields[i].getClass().equals(List.class);

                switch(Classes.fromName(fieldType.nodeType().getName())){
                    case Boolean:
                        if (isList) {
                            fields[i].set(configableObject, config.getBooleanList(objectPrefix + "." + nodeSuffix, (List<Boolean>)fields[i].get(configableObject)));
                        } else {
                            fields[i].set(configableObject, config.getBoolean(objectPrefix + "." + nodeSuffix, (Boolean)fields[i].get(configableObject)));
                        }
                        break;
                    case Double:
                        if (isList) {
                            fields[i].set(configableObject, config.getDoubleList(objectPrefix + "." + nodeSuffix, (List<Double>)fields[i].get(configableObject)));
                        } else {
                            fields[i].set(configableObject, config.getDouble(objectPrefix + "." + nodeSuffix, (Double)fields[i].get(configableObject)));
                        }
                        break;
                    case Integer:
                        if (isList) {
                            fields[i].set(configableObject, config.getIntList(objectPrefix + "." + nodeSuffix, (List<Integer>)fields[i].get(configableObject)));
                        } else {
                            fields[i].set(configableObject, config.getInt(objectPrefix + "." + nodeSuffix, (Integer)fields[i].get(configableObject)));
                        }
                        break;
                    case String:
                        if (isList) {
                            fields[i].set(configableObject, config.getStringList(objectPrefix + "." + nodeSuffix, (List<String>)fields[i].get(configableObject)));
                        } else {
                            fields[i].set(configableObject, config.getString(objectPrefix + "." + nodeSuffix, (String)fields[i].get(configableObject)));
                        }
                        break;
                    case Object:
                        if(isList){
                            fields[i].set(configableObject, config.getList(objectPrefix + "." + nodeSuffix));
                        } else {
                            fields[i].set(configableObject, config.getProperty(objectPrefix + "." + nodeSuffix));
                        }
                        break;
                    default:
                        throw new AnnotationFormatError("Class " + fields[i].getClass().getName() + " is not a valid YAML type.");
                }

            }
        }
    }
}
