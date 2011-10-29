package net.edoxile.bettermechanics.mechanics;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public abstract class CommandableMechanic extends Mechanic {
    public CommandableMechanic(Plugin p) {
        super(p);
    }

    public abstract void onCommand(Player player, Command command, String[] args);
}
