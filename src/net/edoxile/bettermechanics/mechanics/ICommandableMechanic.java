package net.edoxile.bettermechanics.mechanics;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public abstract class ICommandableMechanic implements IMechanic{
    public void onCommand(Player player, Command command, String[] args){
        return;
    }
}
