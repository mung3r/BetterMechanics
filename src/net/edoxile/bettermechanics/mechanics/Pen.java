package net.edoxile.bettermechanics.mechanics;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class Pen implements ISignMechanic{
    public void onSignPowerOn(Block sign) {
        return;
    }

    public void onSignPowerOff(Block sign) {
        return;
    }

    public void onPlayerRightClickSign(Player player, Block sign) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getIdentifier() {
        return "Pen";
    }

    public List<Material> getMechanicActivators() {
        return Arrays.asList(Material.COAL);
    }
}
