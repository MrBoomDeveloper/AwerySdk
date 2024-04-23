package com.mrboomdev.awery.sdk;

import com.mrboomdev.awery.sdk.util.FancyVersion;
import com.mrboomdev.awery.sdk.util.exceptions.MissingImplementationException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

/**
 * A class that provides information about the host application.
 * @author MrBoomDev
 */
public abstract class PlatformApi {
	protected static PlatformApi instance;

	/**
	 * Sets the instance of the PlatformApi class.
	 *
	 * @param instance the PlatformApi instance to set
	 * @author MrBoomDev
	 */
	public static void setInstance(PlatformApi instance) {
		PlatformApi.instance = instance;
	}

	/**
	 * Returns the instance of the PlatformApi class.
	 *
	 * @return the PlatformApi instance
	 * @throws MissingImplementationException if the PlatformApi instance is not set
	 * @author MrBoomDev
	 */
	public static PlatformApi getInstance() {
		if(instance == null) {
			throw new MissingImplementationException("PlatformApi is not set! Please use PlatformApi.setInstance() to set it at the very start of your program.");
        }

		return instance;
	}

	/**
	 * @return A current version of the host application
	 * @author MrBoomDev
	 */
	public abstract FancyVersion getAppVersion();

	/**
	 * @return The name of the host application
	 * @author MrBoomDev
	 */
	public abstract @NotNull String getAppName();

	/**
	 * @return The version of the host JVM library
	 * @author MrBoomDev
	 */
	public abstract FancyVersion getJvmLibraryVersion();

	/**
	 * @return True if the host application was built with a "beta" flavor
	 * @author MrBoomDev
	 */
	public abstract boolean isBeta();
}