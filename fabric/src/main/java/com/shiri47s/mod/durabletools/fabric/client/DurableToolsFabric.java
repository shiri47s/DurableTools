package com.shiri47s.mod.durabletools.fabric.client;

import com.shiri47s.mod.durabletools.fabric.FabricPlatform;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class DurableToolsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        TrinketRendererRegistry.registerRenderer(FabricPlatform.LANTERN_DEFAULT_ITEM, (TrinketRenderer)FabricPlatform.LANTERN_DEFAULT_ITEM);
    }
}
