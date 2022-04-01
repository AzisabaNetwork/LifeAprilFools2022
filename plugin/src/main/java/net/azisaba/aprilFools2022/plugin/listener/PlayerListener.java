package net.azisaba.aprilFools2022.plugin.listener;

import net.azisaba.aprilFools2022.common.util.PacketUtil;
import net.azisaba.aprilFools2022.common.util.Precision;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PacketUtil.inject(e.getPlayer());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getEntity().getLastDamageCause() != null &&
                e.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.CUSTOM &&
                Precision.isAlmostSame(e.getEntity().getLastDamage(), 1145141919810d)) {
            e.setDeathMessage(e.getEntity().getName() + "の炊飯器が爆発した");
        }
    }
}
