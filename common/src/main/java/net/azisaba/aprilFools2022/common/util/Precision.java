package net.azisaba.aprilFools2022.common.util;

public class Precision {
    public static boolean isAlmostSame(double x, double y) {
        return Math.abs(x - y) < 0.00001;
    }
}
