package com.shiri47s.mod;

import net.fabricmc.api.ClientModInitializer;

public class ClientInitializer implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		try {
			DurableTools.INSTANCE.initialize();
		} finally {
			// Do nothing
		}
	}
}