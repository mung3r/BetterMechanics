package net.edoxile.bettermechanics.models;

import net.edoxile.bettermechanics.mechanics.IBlockMechanic;
import net.edoxile.bettermechanics.mechanics.IMechanic;
import net.edoxile.bettermechanics.mechanics.ISignMechanic;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */

public class MechanicsHandler {
    private ArrayList<IMechanic> mechanicsList = new ArrayList<IMechanic>();
    private ArrayList<IBlockMechanic> blockMechanicsList = new ArrayList<IBlockMechanic>();
    private HashMap<String, ISignMechanic> signMechanicMap = new HashMap<String, ISignMechanic>();

    public void addMechanic(IMechanic mechanic) {
        if (mechanic instanceof IBlockMechanic) {
            blockMechanicsList.add((IBlockMechanic) mechanic);
        }
        if (mechanic instanceof ISignMechanic) {
            mechanicsList.add(mechanic);
        }
    }

    public void callPlayerInteractEvent(PlayerInteractEvent event) {
        for (IBlockMechanic mechanic : blockMechanicsList) {
            if ((mechanic.getMechanicActivator() == null
                    || mechanic.getMechanicActivator() == event.getPlayer().getItemInHand().getType())
                    || (mechanic.getMechanicTarget() == null
                    || mechanic.getMechanicTarget().getType() == event.getClickedBlock().getType())) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    mechanic.onBlockRightClick(event.getPlayer(), event.getClickedBlock());
                } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    mechanic.onBlockLeftClick(event.getPlayer(), event.getClickedBlock());
                }
            }
        }
    }

    public void callRedstoneEvent(BlockRedstoneEvent event) {
        for (BlockFace direction : Arrays.asList(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.UP, BlockFace.DOWN)) {
            Block block = event.getBlock().getRelative(direction);
            if (block.getTypeId() == Material.SIGN.getId() || block.getTypeId() == Material.SIGN_POST.getId()) {
                Sign sign = (Sign) block.getState();

            }
        }
    }
}
