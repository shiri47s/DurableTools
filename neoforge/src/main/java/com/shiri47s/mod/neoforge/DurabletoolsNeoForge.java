package com.shiri47s.mod.neoforge;

import com.shiri47s.mod.Durabletools;
import com.shiri47s.mod.neoforge.durabletools.ForgePlatform;
import net.neoforged.fml.common.Mod;

@Mod(com.shiri47s.mod.durabletools.Const.MOD_ID)
public final class DurabletoolsNeoForge {
    public DurabletoolsNeoForge() {
        // Run our common setup.
        Durabletools.init(new ForgePlatform());
    }
}
