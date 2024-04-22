package com.shiri47s.mod.durabletools.fabric;

import com.shiri47s.mod.durabletools.DurableTools;
import net.fabricmc.api.ModInitializer;

public class DurableToolsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        DurableTools.init(new FabricPlatform());
    }
}