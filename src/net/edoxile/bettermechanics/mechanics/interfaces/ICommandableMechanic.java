package net.edoxile.bettermechanics.mechanics.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public interface ICommandableMechanic extends IMechanic {

    public boolean onCommand(CommandSender commandSender, Command command, String[] args);

    public String getIdentifier();

}
