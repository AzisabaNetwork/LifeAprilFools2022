package net.azisaba.aprilFools2022.common;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Logger;

public class AprilFools2022 {
    private static IAprilFools2022 api;

    public static void setAPI(IAprilFools2022 api) {
        if (AprilFools2022.api != null) throw new IllegalStateException("Cannot redefine API singleton");
        AprilFools2022.api = api;
    }

    @NotNull
    public static IAprilFools2022 getAPI() {
        return Objects.requireNonNull(api, "API isn't defined yet");
    }

    @NotNull
    public static VersionDependant getVersionDependant() {
        return getAPI().getVersionDependant();
    }

    @NotNull
    public static Logger getLogger() {
        return getAPI().getLogger();
    }

    @NotNull
    public static Plugin getPlugin() {
        return getAPI().getPlugin();
    }
}
