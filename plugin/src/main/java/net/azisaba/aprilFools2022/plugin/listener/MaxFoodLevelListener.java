package net.azisaba.aprilFools2022.plugin.listener;

import net.azisaba.aprilFools2022.common.AprilFools2022;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class MaxFoodLevelListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerItemConsumeEvent e) {
        ItemStack item = e.getItem();
        if (item == null) return;
        if (AprilFools2022.getVersionDependant().isRiceCooker(item)) {
            e.getPlayer().setFoodLevel(20);
        }
    }
}
