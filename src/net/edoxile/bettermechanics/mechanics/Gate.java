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
@ConfigEntity("gate")
public class Gate implements ISignMechanic {

    @Type(
            node="enabled",
            isList=false
    )
    private boolean enabled = true;

    @Type(
            node="max-length",
            isList=false
    )
    private int maxLength = 32;

    @Type(
            node="max-width",
            isList=false
    )
    private int maxWidth = 3;

    @Type(
            node="allowed-materials",
            isList=true
    )
    private List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

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
