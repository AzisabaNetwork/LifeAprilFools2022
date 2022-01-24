package net.azisaba.aprilFools2022.common;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public interface IAprilFools2022 {
    @NotNull
    VersionDependant getVersionDependant();

    @NotNull
    Logger getLogger();

    @NotNull
    Plugin getPlugin();
}
