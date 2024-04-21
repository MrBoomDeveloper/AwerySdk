package com.mrboomdev.awery.sdk;

import com.mrboomdev.awery.sdk.util.FancyVersion;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import java9.util.stream.Stream;
import java9.util.stream.StreamSupport;

public class PlatformApi {

	/**
	 * @return A current version of the host application
	 */
	public static FancyVersion getAppVersion() {
		throw new UnsupportedOperationException("Stub!");
	}

	/**
	 * @return The name of the host application
	 */
	@Contract(pure = true)
	public static @NotNull String getAppName() {
		throw new UnsupportedOperationException("Stub!");
	}

	/**
	 * @return The version of the host JVM library
	 */
	public static FancyVersion getJvmLibraryVersion() {
		throw new UnsupportedOperationException("Stub!");
	}

	/**
	 * @return True if the host application was built with a "beta" flavor
	 */
	public static boolean isBeta() {
		throw new UnsupportedOperationException("Stub!");
	}

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