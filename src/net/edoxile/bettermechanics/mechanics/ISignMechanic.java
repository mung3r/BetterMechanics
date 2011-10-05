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
public interface ISignMechanic {
    public void onSignPowerOn(Block sign);

    public void onSignPowerOff(Block sign);

    public void onPlayerRightClickSign(Player player, Block sign);

    public String getIdentifier();

    public List<Material> getMechanicActivators();
}
