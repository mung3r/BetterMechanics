package net.edoxile.bettermechanics.mechanics.interfaces;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public interface ISignMechanic extends IMechanic {

    public void onSignPowerOn(Block sign);

    public void onSignPowerOff(Block sign);

    public void onPlayerRightClickSign(Player player, Block sign);

    public void onPlayerLeftClickSign(Player player, Block sign);

    public abstract String getIdentifier();

    public abstract Material getMechanicActivator();
}
