package net.edoxile.configparser;

import net.edoxile.configparser.annotations.ConfigEntityNode;
import net.edoxile.configparser.annotations.NodeType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

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

    protected Plugin plugin = null;
    protected Configuration config = null;
    protected static final Logger logger = Logger.getLogger("Minecraft");

    public ConfigEntity(Plugin p) {
        plugin = p;
        config = p.getConfiguration();
    }

    public void loadConfig() {
        String objectPrefix, nodeSuffix;
        ConfigEntityNode configEntityNode = this.getClass().getAnnotation(ConfigEntityNode.class);
        if (configEntityNode == null)
            return;
        objectPrefix = configEntityNode.value();

        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                NodeType fieldType = fields[i].getAnnotation(NodeType.class);
                if (fieldType != null) {
                    nodeSuffix = fieldType.node();

                    //Have to test this...
                    boolean isList = fields[i].getClass().equals(List.class);

                    switch (Classes.fromName(fieldType.nodeType().getName())) {
                        case Boolean:
                            if (isList) {
                                fields[i].set(this, config.getBooleanList(objectPrefix + "." + nodeSuffix, (List<Boolean>) fields[i].get(this)));
                            } else {
                                fields[i].set(this, config.getBoolean(objectPrefix + "." + nodeSuffix, (Boolean) fields[i].get(this)));
                            }
                            break;
                        case Double:
                            if (isList) {
                                fields[i].set(this, config.getDoubleList(objectPrefix + "." + nodeSuffix, (List<Double>) fields[i].get(this)));
                            } else {
                                fields[i].set(this, config.getDouble(objectPrefix + "." + nodeSuffix, (Double) fields[i].get(this)));
                            }
                            break;
                        case Integer:
                            if (isList) {
                                fields[i].set(this, config.getIntList(objectPrefix + "." + nodeSuffix, (List<Integer>) fields[i].get(this)));
                            } else {
                                fields[i].set(this, config.getInt(objectPrefix + "." + nodeSuffix, (Integer) fields[i].get(this)));
                            }
                            break;
                        case String:
                            if (isList) {
                                fields[i].set(this, config.getStringList(objectPrefix + "." + nodeSuffix, (List<String>) fields[i].get(this)));
                            } else {
                                fields[i].set(this, config.getString(objectPrefix + "." + nodeSuffix, (String) fields[i].get(this)));
                            }
                            break;
                        case Object:
                            if (isList) {
                                fields[i].set(this, config.getList(objectPrefix + "." + nodeSuffix));
                            } else {
                                fields[i].set(this, config.getProperty(objectPrefix + "." + nodeSuffix));
                            }
                            break;
                        default:
                            throw new AnnotationFormatError("Class " + fields[i].getClass().getName() + " is not a valid YAML type.");
                    }

                }
            }
        } catch (IllegalAccessException e) {
            logger.severe("Couldn't access property of entity: " + this.getClass().getName() + " in plugin: " + plugin.getDescription().getName());
        }
    }

    public void saveConfig() {
        String objectPrefix, nodeSuffix;
        ConfigEntityNode configEntityNode = this.getClass().getAnnotation(ConfigEntityNode.class);
        if (configEntityNode == null)
            return;
        objectPrefix = configEntityNode.value();

        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                NodeType fieldType = fields[i].getAnnotation(NodeType.class);
                if (fieldType != null) {
                    nodeSuffix = fieldType.node();
                    config.setProperty(objectPrefix + "." + nodeSuffix, fields[i].get(this));
                }
            }
        } catch (IllegalAccessException e) {
            logger.severe("Couldn't set property of entity: " + this.getClass().getName() + " in plugin: " + plugin.getDescription().getName());
        }
    }
}