package net.edoxile.configparser;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.configparser.annotations.ConfigEntityNode;
import net.edoxile.configparser.annotations.NodeType;
import org.bukkit.util.config.Configuration;

import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */

public abstract class ConfigEntity {

    private enum Classes {
        Integer,
        Boolean,
        String,
        Object,
        Double;

        private static HashMap<String, Classes> classesMap = new HashMap<String, Classes>();

        static {
            for (Classes classes : Classes.values()) {
                classesMap.put(classes.name(), classes);
            }
        }

        public static Classes fromName(String name) {
            return classesMap.get(name);
        }
    }

    protected BetterMechanics plugin = null;
    protected final Logger logger;

    public ConfigEntity(BetterMechanics p) {
        plugin = p;
        logger = plugin.getLogger();
    }

    @SuppressWarnings("unchecked")
    public void loadConfig(Configuration config) {
        String objectPrefix, nodeSuffix;
        ConfigEntityNode configEntityNode = this.getClass().getAnnotation(ConfigEntityNode.class);
        if (configEntityNode == null)
            return;
        objectPrefix = configEntityNode.value();

        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                NodeType fieldType = field.getAnnotation(NodeType.class);
                if (fieldType != null) {
                    nodeSuffix = fieldType.node();

                    //Have to test this...
                    boolean isList = field.getClass().equals(ArrayList.class);

                    switch (Classes.fromName(fieldType.clazz().getName())) {
                        case Boolean:
                            if (isList) {
                                field.set(this, config.getBooleanList(objectPrefix + "." + nodeSuffix, (List<Boolean>) field.get(this)));
                            } else {
                                field.set(this, config.getBoolean(objectPrefix + "." + nodeSuffix, (Boolean) field.get(this)));
                            }
                            break;
                        case Double:
                            if (isList) {
                                field.set(this, config.getDoubleList(objectPrefix + "." + nodeSuffix, (List<Double>) field.get(this)));
                            } else {
                                field.set(this, config.getDouble(objectPrefix + "." + nodeSuffix, (Double) field.get(this)));
                            }
                            break;
                        case Integer:
                            if (isList) {
                                field.set(this, config.getIntList(objectPrefix + "." + nodeSuffix, (List<Integer>) field.get(this)));
                            } else {
                                field.set(this, config.getInt(objectPrefix + "." + nodeSuffix, (Integer) field.get(this)));
                            }
                            break;
                        case String:
                            if (isList) {
                                field.set(this, config.getStringList(objectPrefix + "." + nodeSuffix, (List<String>) field.get(this)));
                            } else {
                                field.set(this, config.getString(objectPrefix + "." + nodeSuffix, (String) field.get(this)));
                            }
                            break;
                        case Object:
                            if (isList) {
                                field.set(this, config.getList(objectPrefix + "." + nodeSuffix));
                            } else {
                                field.set(this, config.getProperty(objectPrefix + "." + nodeSuffix));
                            }
                            break;
                        default:
                            throw new AnnotationFormatError("Class " + field.getClass().getName() + " is not a valid YAML type.");
                    }

                }
            }
        } catch (IllegalAccessException e) {
            logger.severe("Couldn't access property of entity: " + this.getClass().getName() + " in plugin: " + plugin.getDescription().getName());
        }
    }

    public void saveConfig(Configuration config) {
        String objectPrefix, nodeSuffix;
        ConfigEntityNode configEntityNode = this.getClass().getAnnotation(ConfigEntityNode.class);
        if (configEntityNode == null)
            return;
        objectPrefix = configEntityNode.value();

        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                NodeType fieldType = field.getAnnotation(NodeType.class);
                if (fieldType != null) {
                    nodeSuffix = fieldType.node();
                    field.setAccessible(true);
                    if (field.getType().getName().equals(List.class.getName())) {
                        config.setProperty(objectPrefix + "." + nodeSuffix, field.get(this).toString());
                    } else {
                        config.setProperty(objectPrefix + "." + nodeSuffix, field.get(this));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.severe("Couldn't get property of entity: " + this.getClass().getName() + " in plugin: " + plugin.getDescription().getName());
            logger.severe("Field: " + e.getLocalizedMessage());
        }
    }
}