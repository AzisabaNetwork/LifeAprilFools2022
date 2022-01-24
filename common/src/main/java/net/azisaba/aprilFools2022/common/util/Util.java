package net.azisaba.aprilFools2022.common.util;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class Util {
    @NotNull
    public static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    @NotNull
    public static String getImplVersion() {
        String v = getServerVersion();
        if (v.equals("v1_8_R3")) return v;
        if (v.equals("v1_9_R2")) return v;
        if (v.equals("v1_10_R1")) return v;
        if (v.equals("v1_11_R1")) return v;
        if (v.equals("v1_12_R1")) return v;
        if (v.equals("v1_13_R2")) return v;
        if (v.equals("v1_14_R1")) return v;
        if (v.equals("v1_15_R1")) return v;
        if (v.equals("v1_16_R3")) return v;
        if (v.equals("v1_17_R1")) return "v1_17";
        if (v.equals("v1_18_R1")) return "v1_18";
        throw new RuntimeException("Unsupported version: " + v);
    }
}
