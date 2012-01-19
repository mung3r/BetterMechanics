package net.edoxile.bettermechanics.mechanics;

import net.edoxile.bettermechanics.utils.BlockMapper;
import net.edoxile.bettermechanics.utils.MechanicsConfig;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: Edoxile
 */
public class HiddenSwitch {
    private Sign sign;
    private MechanicsConfig.HiddenSwitchConfig config;
    private HashSet<Block> levers;

    public HiddenSwitch(MechanicsConfig c, Sign s, Player p) {
        sign = s;
        config = c.getHiddenSwitchConfig();
    }

    public boolean map() {
        if (!config.enabled)
            return false;
        // levers = BlockMapper.mapAllInCuboidRegion(sign.getBlock(), 1, Material.LEVER);
        levers = BlockMapper.mapHiddenSwitch(sign.getBlock());
        return (!levers.isEmpty());
    }

    public void toggleLevers() {
        for (Block b : levers) {
            b.setData((byte) (b.getData() ^ 0x8));
        }
    }
}
