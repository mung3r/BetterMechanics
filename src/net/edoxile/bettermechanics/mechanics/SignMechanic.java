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
public abstract class SignMechanic extends Mechanic {
    public SignMechanic(Plugin p) {
        super(p);
    }

    public void onSignPowerOn(Block sign){
        return;
    }

    public void onSignPowerOff(Block sign){
        return;
    }

    public void onPlayerRightClickSign(Player player, Block sign){
        return;
    }

    public void onPlayerLeftClickSign(Player player, Block sign){
        return;
    }

    public abstract String getIdentifier();

    public abstract Material getMechanicActivator();
}
