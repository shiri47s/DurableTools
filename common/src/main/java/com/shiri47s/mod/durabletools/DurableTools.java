package com.shiri47s.mod.durabletools;

public class DurableTools
{
	public static final String MOD_ID = Constants.MOD_ID;
	private static IModPlatform platform;

	public static void init(IModPlatform modPlatform) {
		platform = modPlatform;
		ItemModelGenerator.generate(getPlatform());

		platform.initialize();
	}

	public static IModPlatform getPlatform() { return platform; }
}
