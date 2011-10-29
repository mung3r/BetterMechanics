package net.edoxile.bettermechanics.models;

import net.edoxile.bettermechanics.mechanics.IBlockMechanic;
import net.edoxile.bettermechanics.mechanics.ICommandableMechanic;
import net.edoxile.bettermechanics.mechanics.IMechanic;
import net.edoxile.bettermechanics.mechanics.ISignMechanic;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
    private HashMap<Material, IBlockMechanic> blockMechanicMap = new HashMap<Material, IBlockMechanic>();
    private HashMap<String, ISignMechanic> signMechanicMap = new HashMap<String, ISignMechanic>();

    public void addMechanic(IMechanic mechanic) {
        if (mechanic instanceof IBlockMechanic) {
            IBlockMechanic blockMechanic = (IBlockMechanic) mechanic;
            blockMechanicMap.put(blockMechanic.getMechanicTarget(), blockMechanic);
        }
        if (mechanic instanceof ISignMechanic) {
            ISignMechanic blockMechanic = (ISignMechanic) mechanic;
            signMechanicMap.put(blockMechanic.getIdentifier(), blockMechanic);
        }
        if (mechanic instanceof ICommandableMechanic) {
            ICommandableMechanic commandableMechanic = (ICommandableMechanic) commandableMechanic;
        }
        if (mechanic instanceof ISignMechanic) {
            mechanicsList.add(mechanic);
        }
    }

    public void callPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getClickedBlock().getTypeId() == Material.WALL_SIGN.getId() || event.getClickedBlock().getTypeId() == Material.SIGN_POST.getId()) {
            Sign sign = (Sign) event.getClickedBlock().getState();
            ISignMechanic signMechanic = signMechanicMap.get(sign.getLine(2));
            if (signMechanic != null) {
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    signMechanic.onPlayerRightClickSign(event.getPlayer(), event.getClickedBlock());
                } else if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    signMechanic.onPlayerLeftClickSign(event.getPlayer(), event.getClickedBlock());
                }
            }
        } else {
            IBlockMechanic mechanic = blockMechanicMap.get(event.getClickedBlock().getType());
            if (mechanic != null && mechanic.getMechanicActivator() == event.getPlayer().getItemInHand().getType()) {
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
            if (block.getTypeId() == Material.WALL_SIGN.getId() || block.getTypeId() == Material.SIGN_POST.getId()) {
                Sign sign = (Sign) block.getState();
                ISignMechanic signMechanic = signMechanicMap.get(sign.getLine(2));
                if (signMechanic != null) {
                    if (event.getNewCurrent() > 0) {
                        signMechanic.onSignPowerOn(block);
                    } else {
                        signMechanic.onSignPowerOff(block);
                    }
                }
            } else {
                IBlockMechanic mechanic = blockMechanicMap.get(block.getType());
                if (mechanic != null) {
                    if (event.getNewCurrent() > 0) {
                        mechanic.onBlockPowerOn(block);
                    } else {
                        mechanic.onBlockPowerOff(block);
                    }
                }
            }
        }
    }
}
