package net.azisaba.aprilFools2022.plugin;

import net.azisaba.aprilFools2022.common.AprilFools2022;
import net.azisaba.aprilFools2022.common.packet.handler.ChannelHandler;
import net.azisaba.aprilFools2022.plugin.listener.MaxFoodLevelListener;
import net.azisaba.aprilFools2022.plugin.listener.PlayerListener;
import net.azisaba.aprilFools2022.common.util.PacketUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class AprilFools2022Plugin extends JavaPlugin {
    private static final Random RANDOM = new Random();
    private static AprilFools2022Plugin instance;

    @NotNull
    public static AprilFools2022Plugin getInstance() {
        return Objects.requireNonNull(instance, "Plugin is not initialized");
    }

    @Override
    public void onLoad() {
        instance = this;
        AprilFools2022Impl.init();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        if (getConfig().getBoolean("max-food-level-on-ate-rice-cooker", false)) {
            getServer().getPluginManager().registerEvents(new MaxFoodLevelListener(), this);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            PacketUtil.inject(player);
        }
        schedule();
        getLogger().info("All done!");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PacketUtil.eject(player);
        }
    }

    public void schedule() {
        Bukkit.getScheduler().runTaskLater(this, () -> {
            schedule();
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (ItemStack stack : player.getInventory()) {
                    if (stack != null && AprilFools2022.getVersionDependant().isRiceCooker(stack)) {
                        ChannelHandler handler = PacketUtil.getChannelHandler(player);
                        if (handler != null) {
                            handler.freeze();
                        }
                        break;
                    }
                }
            }
        }, 20 * 60 * 5 + RANDOM.nextInt(20 * 60 * 5)); // 5m - 10m
    }
}
