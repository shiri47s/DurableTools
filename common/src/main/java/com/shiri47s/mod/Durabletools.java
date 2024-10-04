package com.shiri47s.mod;

import com.shiri47s.mod.durabletools.Const;
import com.shiri47s.mod.durabletools.ModPlatform;
import com.shiri47s.mod.durabletools.ModelGenerator;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public final class Durabletools {
    private static final DeferredRegister<ItemGroup> TAB_REGISTER = DeferredRegister.create(Const.MOD_ID, RegistryKeys.ITEM_GROUP);
    public static DeferredSupplier<ItemGroup> TAB_SUPPLIER;

    private static ModPlatform modPlatform;

    public static void init(ModPlatform platform) {
        modPlatform = platform;

        TAB_SUPPLIER = TAB_REGISTER.register(Const.MOD_ID, () -> CreativeTabRegistry.create(
                Text.translatable("item.durabletools"),
                () -> new ItemStack(Items.FIREWORK_ROCKET)));
        TAB_REGISTER.register();

        ModelGenerator.Generate();

        modPlatform.initialize();
    }

    public static ModPlatform getPlatform() { return modPlatform; }
}
