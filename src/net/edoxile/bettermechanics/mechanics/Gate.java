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
    private boolean enabled = true;

    @Type(Integer.class)
    private int maxLength = 32;

    @Type(Integer.class)
    private int maxWidth = 3;

    @NodeList
    @Type(Integer.class)
    private List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

    public void setEnabled(boolean isEnabled){
        enabled=isEnabled;
    }

    public void setMaterialList(List<Integer> newMaterialList){
        materialList = newMaterialList;
    }

    public void setMaxLength(int newMaxLength){
        maxLength = newMaxLength;
    }

    public void setMaxWidth(int newMaxWidth){
        maxWidth = newMaxWidth;
    }


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
