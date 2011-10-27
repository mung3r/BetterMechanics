package net.edoxile.bettermechanics.mechanics;

import net.edoxile.configparser.annotations.*;
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
@ConfigEntityNode("gate")
public class Gate implements ISignMechanic  {

    @NodeType(
            node="enabled",
            nodeType=Boolean.class
    )
    public boolean enabled = true;

    @NodeType(
            node="max-length",
            nodeType=Integer.class
    )
    public int maxLength = 32;

    @NodeType(
            node="max-width",
            nodeType=Integer.class
    )
    public int maxWidth = 3;

    @NodeType(
            node="allowed-materials",
            nodeType=Integer.class
    )
    public List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

    public Gate() {
        //Fill config vars
    }

    public void onSignPowerOn(Block sign) {
        //Close gate
    }

    public void onSignPowerOff(Block sign) {
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
