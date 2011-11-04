package net.edoxile.bettermechanics.mechanics;

import net.edoxile.bettermechanics.mechanics.interfaces.ISignMechanic;
import net.edoxile.configparser.ConfigEntity;
import net.edoxile.configparser.annotations.NodeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class Bridge extends ConfigEntity implements ISignMechanic {

    @NodeType(
            node = "enabled",
            clazz = Boolean.class
    )
    private boolean enabled = true;

    @NodeType(
            node = "max-length",
            clazz = Integer.class
    )
    private int maxLength = 128;

    @NodeType(
            node = "allowed-materials",
            clazz = Integer.class
    )
    private List<Integer> materialList = Arrays.asList(Material.IRON_FENCE.getId(), Material.FENCE.getId());

    private ArrayList<Block> blockList = new ArrayList<Block>();
    private List<BlockFace> allowedOrientations = Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);
    private Sign mechanicSign = null;
    private Block otherSide = null;

    public Bridge(Plugin p) {
        super(p);
        this.loadConfig();
    }

    public void onSignPowerOn(Block sign) {
        mechanicSign = (Sign) sign.getState();
        map(null);
        otherSide = null;
        blockList.clear();
    }

    public void onSignPowerOff(Block sign) {
        mechanicSign = (Sign) sign.getState();
    }

    public void onPlayerRightClickSign(Player player, Block sign) {
        mechanicSign = (Sign) sign.getState();
    }

    public void onPlayerLeftClickSign(Player player, Block sign) {
    }

    public String getIdentifier() {
        return "[Bridge]";
    }

    public Material getMechanicActivator() {
        return null;
    }

    private void map(Player player) {
        BlockFace orientation = ((org.bukkit.material.Sign) (mechanicSign.getData())).getFacing().getOppositeFace();
        boolean foundOtherSide = false;
        if (allowedOrientations.contains(orientation)) {
            int travelDistance = maxLength;
            while (travelDistance > 0) {
                travelDistance--;
                otherSide = mechanicSign.getBlock().getRelative(orientation, maxLength - travelDistance);
                if (otherSide.getState() instanceof Sign && ((Sign) otherSide.getState()).getLine(1).startsWith("[Bridge")) {
                    foundOtherSide = true;
                    break;
                }
            }
            if (foundOtherSide) {
                boolean isNorthSouth = (orientation.equals(BlockFace.NORTH) || orientation.equals(BlockFace.SOUTH));
                travelDistance = maxLength - travelDistance - 1;
                for (; travelDistance > 0; travelDistance--) {
                    otherSide = mechanicSign.getBlock().getRelative(orientation, travelDistance);
                    blockList.add(otherSide);
                    if (isNorthSouth) {
                        blockList.add(otherSide.getRelative(BlockFace.NORTH));
                        blockList.add(otherSide.getRelative(BlockFace.SOUTH));
                    } else {
                        blockList.add(otherSide.getRelative(BlockFace.EAST));
                        blockList.add(otherSide.getRelative(BlockFace.WEST));
                    }
                }
            }
        } else {
            if (player != null)
                player.sendMessage(ChatColor.DARK_RED + "Hmm, seems like this sing is not placed correctly...");
        }
    }
}
