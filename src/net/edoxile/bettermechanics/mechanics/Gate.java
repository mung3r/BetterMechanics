package net.edoxile.bettermechanics.mechanics;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.bettermechanics.mechanics.interfaces.ISignMechanic;
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
public class Gate extends ConfigEntity implements ISignMechanic {

    @NodeType(
            node = "enabled",
            clazz = Boolean.class
    )
    private boolean enabled = true;

    @NodeType(
            node = "max-length",
            clazz = Integer.class
    )
    private int maxLength = 32;

    @NodeType(
            node = "max-width",
            clazz = Integer.class
    )
    private int maxWidth = 3;

    @NodeType(
            node = "allowed-materials",
            clazz = Integer.class
    )
    private List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

    public Gate(Plugin p) {
        super(p);
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

    public void onPlayerLeftClickSign(Player player, Block sign) {
    }

    public String getIdentifier() {
        return "[Gate]";
    }

    public Material getMechanicActivator() {
        return null;
    }
}
