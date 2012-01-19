package net.edoxile.bettermechanics.models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.bettermechanics.exceptions.ConfigWriteException;
import net.edoxile.bettermechanics.utils.CauldronCookbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Edoxile
 */
public class MechanicsConfig {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static BetterMechanics plugin;
    private static Configuration config;

    public BridgeConfig bridgeConfig;
    public GateConfig gateConfig;
    public DoorConfig doorConfig;
    public LiftConfig liftConfig;
    public TeleLiftConfig teleLiftConfig;
    public HiddenSwitchConfig hiddenSwitchConfig;
    public AmmeterConfig ammeterConfig;
    public CauldronConfig cauldronConfig;
    public PermissionConfig permissionConfig;
    public PenConfig penConfig;
    public boolean useTweakcraftUtils;

    public MechanicsConfig(BetterMechanics p) throws ConfigWriteException {
        plugin = p;
        config = plugin.getConfiguration();
        config.load();
        if (config == null) {
            createConfig();
        } else {
            createConfig();
        }

        bridgeConfig = new BridgeConfig();
        gateConfig = new GateConfig();
        doorConfig = new DoorConfig();
        liftConfig = new LiftConfig();
        teleLiftConfig = new TeleLiftConfig();
        hiddenSwitchConfig = new HiddenSwitchConfig();
        ammeterConfig = new AmmeterConfig();
        cauldronConfig = new CauldronConfig();
        permissionConfig = new PermissionConfig();
        penConfig = new PenConfig();
        useTweakcraftUtils = config.getBoolean("use-tweakcraftutils", false);
    }

    public void reloadCauldronConfig() {
        cauldronConfig = new CauldronConfig();
    }

    public static class BridgeConfig {
        public final boolean enabled;
        public final Set<Material> materials;
        public final int maxLength;

        public BridgeConfig() {
            enabled = config.getBoolean("bridge.enabled", true);
            maxLength = config.getInt("bridge.max-length", 32);
            List<Integer> list = config.getIntList("bridge.allowed-materials", Arrays.asList(3, 4, 5, 22, 35, 41, 42, 45, 47, 57, 87, 88, 89, 91));
            Set<Material> hashSet = new HashSet<Material>();
            for (int m : list)
                hashSet.add(Material.getMaterial(m));
            materials = Collections.unmodifiableSet(hashSet);
        }

        public boolean canUseBlock(Material b) {
            return materials.contains(b);
        }
    }

    public class GateConfig {
        public final boolean enabled;
        public final int maxLength;
        public final int maxWidth;
        public final int maxHeight;

        public GateConfig() {
            enabled = config.getBoolean("door.enabled", true);
            maxHeight = config.getInt("door.max-height", 32);
            maxLength = config.getInt("door.max-length", 32);
            maxWidth = config.getInt("door.max-width", 3);
        }
    }

    public class PenConfig {
        public final boolean enabled;
        public final Material penMaterial;

        public PenConfig() {
            enabled = config.getBoolean("pen.enabled", true);
            penMaterial = Material.getMaterial(config.getInt("pen.material", 280));
        }
    }

    public class DoorConfig {
        public final boolean enabled;
        public final int maxHeight;
        public final Set<Material> materials;

        public DoorConfig() {
            enabled = config.getBoolean("door.enabled", true);
            maxHeight = config.getInt("door.max-height", 32);
            List<Integer> list = config.getIntList("door.allowed-materials", Arrays.asList(3, 4, 5, 22, 35, 41, 42, 45, 47, 57, 87, 88, 89, 91));
            Set<Material> hashSet = new HashSet<Material>();
            for (int m : list)
                hashSet.add(Material.getMaterial(m));
            materials = Collections.unmodifiableSet(hashSet);
        }

        public boolean canUseBlock(Material b) {
            return materials.contains(b);
        }
    }

    public class LiftConfig {
        public final boolean enabled;
        public final int maxSearchHeight;

        public LiftConfig() {
            enabled = config.getBoolean("lift.enabled", true);
            maxSearchHeight = config.getInt("lift.max-search-height", 32);
        }
    }

    public class TeleLiftConfig {
        public final boolean enabled;

        public TeleLiftConfig() {
            enabled = config.getBoolean("telelift.enabled", true);
        }
    }

    public class HiddenSwitchConfig {
        public final boolean enabled;

        public HiddenSwitchConfig() {
            enabled = config.getBoolean("hidden-switch.enabled", true);
        }
    }

    public class CauldronConfig {
        public final boolean enabled;
        public final net.edoxile.bettermechanics.utils.CauldronCookbook cauldronCookbook;

        public CauldronConfig() {
            if (config.getBoolean("cauldron.enabled", true)) {
                cauldronCookbook = new CauldronCookbook(plugin);
                if (cauldronCookbook.size() > 0) {
                    enabled = true;
                } else {
                    log.warning("[BetterMechanics] Disabled cauldron because there were no recipes found in the config.");
                    enabled = false;
                }
            } else {
                cauldronCookbook = null;
                enabled = false;
            }
        }
    }

    public class AmmeterConfig {
        public final boolean enabled;

        public AmmeterConfig() {
            enabled = config.getBoolean("ammeter.enabled", true);
        }
    }

    public class PermissionConfig {
        public final boolean useWorldGuard;
        private WorldGuardPlugin worldGuard = null;

        public PermissionConfig() {
            useWorldGuard = config.getBoolean("use-worldguard", true);
            if (useWorldGuard) {
                this.setupWorldGuard();
                log.info("[BetterMechanics] Using WorldGuard");
            }
        }

        private void setupWorldGuard() {
            Plugin wg = plugin.getServer().getPluginManager().getPlugin("WorldGuard");
            if (worldGuard == null) {
                if (wg != null) {
                    worldGuard = (WorldGuardPlugin) wg;
                }
            }
        }

        public boolean checkWorldGuard(Player player, Block clickedBlock) {
            if (worldGuard == null) {
                return true;
            } else {
                return player.isOp() || worldGuard.canBuild(player, clickedBlock);
            }
        }

        public boolean check(Player player, String type, Block clickedBlock, boolean skipCreateZones, boolean skipHitZones) {
            boolean allowed = false;
            if (player.hasPermission("bettermechanics." + type)) {
                allowed = checkWorldGuard(player, clickedBlock);
            }

            if (!allowed) {
                player.sendMessage(ChatColor.RED + "O oh! Seems like you don't have permissions for this!");
            }

            return allowed;
        }

        public boolean check(Player player, String type, Block clickedBlock, boolean skipZones) {
            return check(player, type, clickedBlock, skipZones, true);
        }
    }

    public PermissionConfig getPermissionConfig() {
        return this.permissionConfig;
    }

    public BridgeConfig getBridgeConfig() {
        return this.bridgeConfig;
    }

    public GateConfig getGateConfig() {
        return this.gateConfig;
    }

    public DoorConfig getDoorConfig() {
        return this.doorConfig;
    }

    public HiddenSwitchConfig getHiddenSwitchConfig() {
        return this.hiddenSwitchConfig;
    }

    public LiftConfig getLiftConfig() {
        return this.liftConfig;
    }

    public TeleLiftConfig getTeleLiftConfig() {
        return this.teleLiftConfig;
    }

    public AmmeterConfig getAmmeterConfig() {
        return this.ammeterConfig;
    }

    public CauldronConfig getCauldronConfig() {
        return this.cauldronConfig;
    }

    public PenConfig getPenConfig() {
        return this.penConfig;
    }

    private void createConfig() throws ConfigWriteException {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.canRead()) {
            try {
                configFile.getParentFile().mkdirs();
                JarFile jar = new JarFile(plugin.getConfigFile());
                JarEntry entry = jar.getJarEntry("config.yml");
                InputStream is = jar.getInputStream(entry);
                FileOutputStream os = new FileOutputStream(configFile);
                byte[] buf = new byte[(int) entry.getSize()];
                is.read(buf, 0, (int) entry.getSize());
                os.write(buf);
                os.close();
                plugin.getConfiguration().load();
            } catch (Exception e) {
                throw new ConfigWriteException();
            }
        }
    }
}
