package old.bettermechanics.mechanics;

import old.bettermechanics.utils.MechanicsConfig;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by IntelliJ IDEA.
 * User: s111172
 * Date: 20-9-11
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class Cycler {
    public static boolean cycle(Player p, Block b, MechanicsConfig c) {
        if (c.getPermissionConfig().checkZonesCreate(p, b)) {
            byte newByte = (byte) (b.getData() + 1);
            newByte = (newByte == 6) ? 2 : newByte;
            b.setData(newByte);
            return true;
        } else {
            return false;
        }
    }
}
