package net.edoxile.bettermechanics.listeners;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.bettermechanics.models.MechanicsHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
public class BMBlockListener extends BlockListener {
    private BetterMechanics plugin;
    private MechanicsHandler mechanicsHandler;

    public BMBlockListener(BetterMechanics bm) {
        plugin = bm;
        mechanicsHandler = bm.getMechanicsHandler();
    }

    public void onBlockRedstoneChange(BlockRedstoneEvent event) {
        if (event.getNewCurrent() == event.getOldCurrent() || event.getNewCurrent() > 0 && event.getOldCurrent() > 0)
            return;
        mechanicsHandler.callRedstoneEvent(event);
    }

    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled())
            return;
        mechanicsHandler.callBlockEvent(event);
    }

    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled())
            return;
        mechanicsHandler.callBlockEvent(event);
    }
}
