package net.edoxile.bettermechanics.mechanics;

import net.edoxile.configparser.ConfigEntity;
import org.bukkit.plugin.Plugin;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public abstract class Mechanic extends ConfigEntity {
    public Mechanic(Plugin p){
        super(p);
    }
}
