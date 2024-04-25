package com.shiri47s.mod.durabletools;

public class DurableTools
{
	public static final String MOD_ID = Constants.MOD_ID;
	private static final TickListener TICK_LISTENER = new TickListener();
	private static IModPlatform platform;

	public static void init(IModPlatform modPlatform) {
		platform = modPlatform;
		ItemModelGenerator.generate(getPlatform());

		platform.initialize();

		TICK_LISTENER.listen(player -> {
			// do nothing
		});
	}

	public static IModPlatform getPlatform() { return platform; }
}
