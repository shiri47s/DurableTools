package com.shiri47s.mod.durabletools.forge;

import com.shiri47s.mod.durabletools.AbstractModPlatform;
import com.shiri47s.mod.durabletools.Enums;
import com.shiri47s.mod.durabletools.forge.items.ForgeNetheriteElytraItem;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ForgePlatform extends AbstractModPlatform {

    @Override
    public void initialize() {

    }

    @Override
    public boolean isOf(ItemStack stack, Enums.ItemType type) {
        if (Objects.requireNonNull(type) == Enums.ItemType.NetheriteElytra) {
            return stack.getItem() instanceof ForgeNetheriteElytraItem;
        }
        return false;
    }

    @Override
    public Item getCustomElytraItem() {
        return new ForgeNetheriteElytraItem();
    }

    @Override
    public boolean isLoadedMod(String modName) {
        return false;
    }
}
