package net.azisaba.aprilFools2022.plugin.listener;

import net.azisaba.aprilFools2022.common.AprilFools2022;
import net.azisaba.aprilFools2022.common.packet.handler.ChannelHandler;
import net.azisaba.aprilFools2022.common.util.PacketUtil;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PacketUtil.inject(e.getPlayer());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        if (item == null) return;
        if (AprilFools2022.getVersionDependant().isRiceCooker(item)) {
            e.setUseItemInHand(Event.Result.DENY);
            e.setUseInteractedBlock(Event.Result.DENY);
            ChannelHandler handler = PacketUtil.getChannelHandler(e.getPlayer());
            if (handler != null) {
                handler.freeze();
            }
        }
    }
}
