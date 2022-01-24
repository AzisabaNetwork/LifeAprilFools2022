package net.azisaba.aprilFools2022.plugin;

import net.azisaba.aprilFools2022.plugin.listener.PlayerListener;
import net.azisaba.aprilFools2022.common.util.PacketUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AprilFools2022Plugin extends JavaPlugin {
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
        for (Player player : Bukkit.getOnlinePlayers()) {
            PacketUtil.inject(player);
        }
        getLogger().info("All done!");
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PacketUtil.eject(player);
        }
    }
}
