package net.edoxile.bettermechanics.mechanics.interfaces;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public interface IBlockMechanic extends IMechanic {

    public void onBlockRightClick(Player player, Block block);

    public void onBlockLeftClick(Player player, Block block);

    public void onBlockPlace(Player player, Block block);

    public void onBlockBreak(Player player, Block block);

    public void onBlockPowerOn(Block block);

    public void onBlockPowerOff(Block block);

    public boolean isTriggeredByRedstone();

    public Material getMechanicActivator();

    public Material getMechanicTarget();
}
