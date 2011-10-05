package net.edoxile.bettermechanics.mechanics;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public interface IBlockMechanic {
    public void onBlockRightClick(Player player, Block block);

    public void onBlockLeftClick(Player player, Block block);

    public void onBlockPowerOn(Player player, Block block);

    public void onBlockPowerOff(Player player, Block block);

    public List<Material> getMechanicActivators();

    public List<Block> getMechanicTargets();
}
