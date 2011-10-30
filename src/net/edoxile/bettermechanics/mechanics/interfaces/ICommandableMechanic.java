package net.edoxile.bettermechanics.mechanics.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public interface ICommandableMechanic extends IMechanic {

    public void onCommand(CommandSender commandSender, Command command, String[] args);

    public String getIdentifier();

}
