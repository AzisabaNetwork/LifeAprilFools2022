package net.azisaba.aprilFools2022.v1_16_R3;

import io.netty.channel.Channel;
import net.azisaba.aprilFools2022.common.VersionDependant;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VersionDependantImpl implements VersionDependant {
    @Override
    public @NotNull Channel getChannel(@NotNull Player player) {
        return ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
    }

    @Override
    public @NotNull Material getSteak() {
        return Material.COOKED_BEEF;
    }
}
