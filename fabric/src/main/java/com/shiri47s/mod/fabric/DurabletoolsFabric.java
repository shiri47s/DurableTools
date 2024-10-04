package com.shiri47s.mod.fabric;

import com.shiri47s.mod.Durabletools;
import com.shiri47s.mod.fabric.durabletools.FabricPlatform;
import net.fabricmc.api.ModInitializer;

public final class DurabletoolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Durabletools.init(new FabricPlatform());
    }
}
