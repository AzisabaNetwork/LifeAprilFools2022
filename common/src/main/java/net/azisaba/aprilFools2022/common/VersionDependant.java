package net.azisaba.aprilFools2022.common;

import io.netty.channel.Channel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface VersionDependant {
    @NotNull
    Channel getChannel(@NotNull Player player);

    @NotNull
    Material getSteak();

    default boolean isRiceCooker(@NotNull ItemStack item) {
        return item.getType() == getSteak() &&
                item.hasItemMeta() &&
                item.getItemMeta().hasDisplayName() &&
                item.getItemMeta().hasLore() &&
                ChatColor.stripColor(item.getItemMeta().getDisplayName()).contains("\u708a\u98ef\u5668");
    }

    void createExplosionEffect(@NotNull Player player, float deltaX, float deltaY, float deltaZ, float pitch);
}
