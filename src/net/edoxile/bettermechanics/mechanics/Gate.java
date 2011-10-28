package net.edoxile.bettermechanics.mechanics;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.configparser.ConfigEntity;
import net.edoxile.configparser.annotations.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
@ConfigEntityNode("gate")
public class Gate extends ConfigEntity implements ISignMechanic  {

    @NodeType(
            node="enabled",
            nodeType=Boolean.class
    )
    private boolean enabled = true;

    @NodeType(
            node="max-length",
            nodeType=Integer.class
    )
    private int maxLength = 32;

    @NodeType(
            node="max-width",
            nodeType=Integer.class
    )
    private int maxWidth = 3;

    @NodeType(
            node="allowed-materials",
            nodeType=Integer.class
    )
    private List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

    public Gate(BetterMechanics bm) {
        super(bm);
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
