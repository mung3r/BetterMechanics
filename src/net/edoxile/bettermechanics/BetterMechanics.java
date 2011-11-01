package net.edoxile.bettermechanics;

import net.edoxile.bettermechanics.mechanics.Gate;
import net.edoxile.bettermechanics.mechanics.Pen;
import net.edoxile.bettermechanics.models.MechanicsHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
    private static boolean debugMode;
    private MechanicsHandler mechanicsHandler = new MechanicsHandler();

    public void onEnable() {
        mechanicsHandler.addMechanic(new Gate(this));
        mechanicsHandler.addMechanic(new Pen(this));
        debugMode = getConfiguration().getBoolean("debug-mode", false);
        log("Enabled! Version: " + getDescription().getVersion() + ".");
    }

    public void onDisable() {
        log("Disabled.");
    }

    public static void log(String msg) {
        log(msg, Level.INFO);
    }

    public static void log(String msg, Level level) {
        if (debugMode && level == Level.FINEST)
            logger.log(level, "[BetterMechanics] " + msg);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args){
        return mechanicsHandler.callCommandEvent(command, commandSender, args);
    }

    public MechanicsHandler getMechanicsHandler() {
        return mechanicsHandler;
    }
}