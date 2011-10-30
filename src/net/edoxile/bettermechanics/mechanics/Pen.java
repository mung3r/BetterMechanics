package net.edoxile.bettermechanics.mechanics;

import net.edoxile.bettermechanics.mechanics.interfaces.ICommandableMechanic;
import net.edoxile.bettermechanics.mechanics.interfaces.ISignMechanic;
import net.edoxile.configparser.ConfigEntity;
import net.edoxile.configparser.annotations.ConfigEntityNode;
import net.edoxile.configparser.annotations.NodeType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.persistence.NamedNativeQueries;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Edoxile
 */
@ConfigEntityNode("pen")
public class Pen extends ConfigEntity implements ISignMechanic, ICommandableMechanic {

    @NodeType(
            node = "enabled",
            clazz = Boolean.class
    )
    private boolean enabled;

    @NodeType(
            node = "tool",
            clazz = Integer.class
    )
    private int tool;

    public Pen(Plugin p) {
        super(p);
    }

    public void onSignPowerOn(Block sign) {
    }

    public void onSignPowerOff(Block sign) {
    }

    public void onPlayerRightClickSign(Player player, Block sign) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void onPlayerLeftClickSign(Player player, Block sign) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getIdentifier() {
        return "";
    }

    public Material getMechanicActivator() {
        return Material.getMaterial(tool);
    }

    public void onCommand(Player player, Command command, String[] args) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
