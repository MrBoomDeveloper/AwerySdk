package com.mrboomdev.awery.sdk.util;

import org.jetbrains.annotations.Nullable;

/**
 * A little helper-class to cache a value of a single object.
 * @author MrBoomDev
 * @param <K> The key
 * @param <V> The value
 */
public class CachedValue<K, V> {
	private K key;
	private V value;

	/**
	 * Creates an empty CachedValue
	 * @author MrBoomDev
	 */
	public CachedValue() {}

	/**
	 * Creates a CachedValue with the given key and value
	 * @author MrBoomDev
	 */
	public CachedValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Sets the key and value of the CachedValue object.
	 * @param key the key to be set
	 * @param value the value to be set
	 */
	public void set(K key, V value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Retrieves the value if the given key was the same as the last set key.
	 */
	@Nullable
	public V get(K key) {
		if(key == this.key) {
			return value;
		}

		return null;
	}

	/**
	 * Retrieves the key associated with the CachedValue object.
	 * @return the key associated with the CachedValue object
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Retrieves the value of the object.
	 * @return the value of the object
	 */
	public V getValue() {
		return value;
	}
}