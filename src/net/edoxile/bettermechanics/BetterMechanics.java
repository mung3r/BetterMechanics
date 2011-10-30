package net.edoxile.bettermechanics;

import net.edoxile.bettermechanics.models.MechanicsHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class BetterMechanics extends JavaPlugin {
    private static Logger logger = Logger.getLogger("Minecraft");
    private static final boolean debugMode = true;
    private MechanicsHandler mechanicsHandler = new MechanicsHandler();

    public void onEnable() {
        log("Enabled! Version: " + getDescription().getVersion() + ".");
    }

    public void onDisable() {
        log("Disabled.");
    }

    public static void log(String msg){
        log(msg, Level.INFO);
    }

    public static void log(String msg, Level level){
        if((!debugMode) || (debugMode && level == Level.FINEST))
            logger.log(level, "[BetterMechanics] " + msg);
    }

    public MechanicsHandler getMechanicsHandler(){
        return mechanicsHandler;
    }
}
