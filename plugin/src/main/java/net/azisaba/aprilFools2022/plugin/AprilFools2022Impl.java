package net.azisaba.aprilFools2022.plugin;

import net.azisaba.aprilFools2022.common.IAprilFools2022;
import net.azisaba.aprilFools2022.common.AprilFools2022;
import net.azisaba.aprilFools2022.common.VersionDependant;
import net.azisaba.aprilFools2022.common.util.Util;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class AprilFools2022Impl implements IAprilFools2022 {
    private final VersionDependant versionDependant;

    private AprilFools2022Impl() {
        try {
            this.versionDependant = (VersionDependant) Class.forName("net.azisaba.aprilFools2022." + Util.getImplVersion() + ".VersionDependantImpl").getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public VersionDependant getVersionDependant() {
        return versionDependant;
    }

    @NotNull
    @Override
    public Logger getLogger() {
        return getPlugin().getLogger();
    }

    @NotNull
    @Override
    public Plugin getPlugin() {
        return AprilFools2022Plugin.getInstance();
    }

    static void init() {}

    static {
        AprilFools2022.setAPI(new AprilFools2022Impl());
    }
}
