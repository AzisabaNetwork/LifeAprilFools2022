package net.azisaba.aprilFools2022.common.util;

@FunctionalInterface
public interface ThrowableFunction<T, R> {
    R apply(T t) throws Exception;
}
