package net.azisaba.aprilFools2022.plugin;

import net.azisaba.aprilFools2022.common.AprilFools2022;
import net.azisaba.aprilFools2022.common.util.PacketUtil;
import net.azisaba.aprilFools2022.plugin.listener.MaxFoodLevelListener;
import net.azisaba.aprilFools2022.plugin.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class AprilFools2022Plugin extends JavaPlugin {
    private static final Random RANDOM = new Random();
    public static final Map<UUID, Double> LAST_DAMAGE = new HashMap<>();
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
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.isDead() && RANDOM.nextInt(4) == 2) {
                    // 1 in ~3 minutes
                    for (ItemStack stack : player.getInventory()) {
                        if (stack != null && AprilFools2022.getVersionDependant().isRiceCooker(stack)) {
                            int count = RANDOM.nextInt(25) + 1;
                            for (int i = 0; i < count; i++) {
                                Bukkit.getScheduler().runTaskLater(this, () -> {
                                    float deltaX = RANDOM.nextFloat() - 0.5f;
                                    float deltaY = RANDOM.nextFloat() - 0.5f;
                                    float deltaZ = RANDOM.nextFloat() - 0.5f;
                                    float pitch = RANDOM.nextFloat();
                                    AprilFools2022.getVersionDependant().createExplosionEffect(player, deltaX, deltaY, deltaZ, pitch);
                                    double damage = RANDOM.nextInt(Math.max(100, 100 + (int) player.getHealth() * 10)) / 100.0;
                                    LAST_DAMAGE.put(player.getUniqueId(), (double) (float) damage);
                                    player.damage(damage); // 0 - 2.0
                                    player.setNoDamageTicks(0);
                                }, i);
                            }
                            break;
                        }
                    }
                }
            }
        }, 20 * 10, 20 * 10);
    }
}
