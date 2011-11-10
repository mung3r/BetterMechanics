package net.edoxile.bettermechanics.listeners;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.bettermechanics.models.MechanicsHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */

public class BMPlayerListener extends PlayerListener {
    private BetterMechanics plugin;
    private MechanicsHandler mechanicsHandler;

    public BMPlayerListener(BetterMechanics bm) {
        plugin = bm;
        mechanicsHandler = bm.getMechanicsHandler();
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.isCancelled())
            return;
        mechanicsHandler.callPlayerInteractEvent(event);
    }
}
