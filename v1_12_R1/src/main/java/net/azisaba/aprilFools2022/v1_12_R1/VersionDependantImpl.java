package net.azisaba.aprilFools2022.v1_12_R1;

import io.netty.channel.Channel;
import net.azisaba.aprilFools2022.common.VersionDependant;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
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

    @Override
    public void createExplosionEffect(@NotNull Player player, float deltaX, float deltaY, float deltaZ, float pitch) {
        Location location = player.getLocation();
        location.add(deltaX, deltaY, deltaZ);
        player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, location, 1);
        player.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, pitch);
    }
}
