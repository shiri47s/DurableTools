package com.shiri47s.mod.durabletools.fabric.mixin;

import com.shiri47s.mod.durabletools.DurableTools;
import com.shiri47s.mod.durabletools.IModPlatform;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class DurableToolsMixinPlugin implements IMixinConfigPlugin {
    private static final IModPlatform PLATFORM = DurableTools.getPlatform();
    private static final String EmptyString = "";
    @Override
    public void onLoad(String mixinPackage) {
        // do nothing.
    }

    @Override
    public String getRefMapperConfig() {
        return EmptyString;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.equals("com.shiri47s.mod.durabletools.fabric.mixin.FireworkRocketEntityMixin")) {
            return !FabricLoaderImpl.INSTANCE.isModLoaded("mythicmetals");
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // do nothing.
    }

    @Override
    public List<String> getMixins() {
        return List.of();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // do nothing.
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // do nothing.
    }
}
