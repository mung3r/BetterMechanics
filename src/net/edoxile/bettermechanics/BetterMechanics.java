package net.edoxile.bettermechanics;

import net.edoxile.bettermechanics.listeners.BMBlockListener;
import net.edoxile.bettermechanics.listeners.BMPlayerListener;
import net.edoxile.bettermechanics.mechanics.Bridge;
import net.edoxile.bettermechanics.mechanics.Gate;
import net.edoxile.bettermechanics.mechanics.Pen;
import net.edoxile.bettermechanics.models.MechanicsHandler;
import net.edoxile.bettermechanics.models.PermissionType;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockListener;
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
    private BMPlayerListener playerListener = new BMPlayerListener(this);
    private BMBlockListener blockListener = new BMBlockListener(this);

    public void onEnable() {
        //Register different Mechanics
        /*mechanicsHandler.addMechanic(new Gate(this));
        mechanicsHandler.addMechanic(new Pen(this));*/
        mechanicsHandler.addMechanic(new Bridge(this));

        //TODO: Register different events
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.REDSTONE_CHANGE, blockListener, Event.Priority.Normal, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);

        debugMode = getConfiguration().getBoolean("debug-mode", false);
        log("Enabled! Version: " + getDescription().getVersion() + ".");
    }

    public void onDisable() {
        log("Disabled.");
    }

    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args){
        return mechanicsHandler.callCommandEvent(command, commandSender, args);
    }

    public MechanicsHandler getMechanicsHandler() {
        return mechanicsHandler;
    }

    public boolean hasPermission(Player player, Block block, PermissionType type){
        return true;
    }

    public static void log(String msg) {
        log(msg, Level.INFO);
    }

    public static void log(String msg, Level level) {
        if (debugMode && level == Level.FINEST)
            logger.log(level, "[BetterMechanics] " + msg);
    }
}