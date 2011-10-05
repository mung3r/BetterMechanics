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
public class Gate implements ISignMechanic {
    private class config{
        private final boolean isEnabled = true;
        private final int maxLength = 32;
        private final int maxWidth = 3;
    }
    public Gate(BMConfig configHandler){
        //Fill config vars
    }

    public void onSignPowerOn(Block sign) {
        //Close gate
    }

    public void onSignPowerOff(Block sign){
        //Open gate
    }

    public void onPlayerRightClickSign(Player player, Block sign) {
        //Toggle gate
    }

    public String getIdentifier() {
        return "Gate";
    }

    public List<Material> getMechanicActivators() {
        return null;
    }
}
