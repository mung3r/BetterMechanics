package net.edoxile.bettermechanics.mechanics;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public abstract class BlockMechanic extends Mechanic {
    public BlockMechanic(Plugin p) {
        super(p);
    }

    public void onBlockRightClick(Player player, Block block){
        return;
    }

    public void onBlockLeftClick(Player player, Block block){
        return;
    }

    public void onBlockPowerOn(Block block){
        return;
    }

    public void onBlockPowerOff(Block block){
        return;
    }

    public abstract Material getMechanicActivator();

    public abstract Material getMechanicTarget();
}
