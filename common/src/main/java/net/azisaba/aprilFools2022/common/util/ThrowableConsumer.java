package net.azisaba.aprilFools2022.common.util;

@FunctionalInterface
public interface ThrowableConsumer<T> {
    void accept(T t) throws Exception;
}
