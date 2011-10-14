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
@ConfigEntity
public class Gate implements ISignMechanic {

    @Type(Boolean.class)
    private final boolean enabled = true;

    @Type(Integer.class)
    private final int maxLength = 32;

    @Type(Integer.class)
    private final int maxWidth = 3;

    @NodeList
    @Type(Integer.class)
    private final List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());


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
