package com.mrboomdev.awery.sdk;

import java9.util.stream.Stream;
import java9.util.stream.StreamSupport;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class PlatformSupport {

    @NotNull
    @Contract("_ -> new")
    public static <E> Stream<E> stream(Collection<E> e) {
        return StreamSupport.stream(e);
    }

    @SafeVarargs
    @NotNull
    @Contract("_ -> new")
    public static <E> Stream<E> stream(E... e) {
        return StreamSupport.stream(Arrays.asList(e));
    }

    @NotNull
    @Contract("_ -> new")
    public static <K, V> Stream<Map.Entry<K,V>> stream(@NotNull Map<K, V> map) {
        return StreamSupport.stream(map.entrySet());
    }
}
