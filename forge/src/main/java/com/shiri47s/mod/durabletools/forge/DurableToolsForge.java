package com.shiri47s.mod.durabletools.forge;

import dev.architectury.platform.forge.EventBuses;
import com.shiri47s.mod.durabletools.DurableTools;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DurableTools.MOD_ID)
public class DurableToolsForge {
    public DurableToolsForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(DurableTools.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        DurableTools.init(new ForgePlatform());
    }
}