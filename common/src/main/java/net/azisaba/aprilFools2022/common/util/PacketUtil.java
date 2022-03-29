package net.azisaba.aprilFools2022.common.util;

import net.azisaba.aprilFools2022.common.AprilFools2022;
import net.azisaba.aprilFools2022.common.packet.handler.ChannelHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

public class PacketUtil {
    public static final boolean DISABLED = true;

    public static void inject(@NotNull Player player) {
        if (DISABLED) return;
        ChannelHandler handler = new ChannelHandler();
        try {
            AprilFools2022.getVersionDependant().getChannel(player).pipeline().addBefore("packet_handler", "april_fools_2022", handler);
            AprilFools2022.getLogger().info("Injected packet handler for " + player.getName());
        } catch (NoSuchElementException ex) {
            Bukkit.getScheduler().runTaskLater(AprilFools2022.getPlugin(), () -> {
                if (!player.isOnline()) return;
                try {
                    AprilFools2022.getVersionDependant().getChannel(player).pipeline().addBefore("packet_handler", "april_fools_2022", handler);
                    AprilFools2022.getLogger().info("Injected packet handler for " + player.getName());
                } catch (NoSuchElementException ignore) {
                    AprilFools2022.getLogger().warning("Failed to inject packet handler to " + player.getName());
                }
            }, 10);
        }
    }

    public static void eject(@NotNull Player player) {
        if (DISABLED) return;
        try {
            if (AprilFools2022.getVersionDependant().getChannel(player).pipeline().get("april_fools_2022") != null) {
                AprilFools2022.getVersionDependant().getChannel(player).pipeline().remove("april_fools_2022");
                AprilFools2022.getLogger().info("Ejected packet handler from " + player.getName());
            }
        } catch (RuntimeException e) {
            AprilFools2022.getLogger().warning("Failed to eject packet handler from " + player.getName() + ", are they already disconnected?");
        }
    }

    @Nullable
    public static ChannelHandler getChannelHandler(@NotNull Player player) {
        if (DISABLED) return null;
        try {
            return AprilFools2022.getVersionDependant().getChannel(player).pipeline().get(ChannelHandler.class);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
