package com.shiri47s.mod.durabletools.forge;

import com.shiri47s.mod.durabletools.AbstractModPlatform;
import com.shiri47s.mod.durabletools.Enums;
import com.shiri47s.mod.durabletools.forge.items.DurableLanternItem;
import com.shiri47s.mod.durabletools.forge.items.NetheriteLanternItem;
import com.shiri47s.mod.durabletools.items.NetheriteElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public class ForgePlatform extends AbstractModPlatform {
    public static Item LANTERN_DEFAULT_ITEM;
    public static Item LANTERN_UPGRADED_ITEM;

    @Override
    public boolean isFabric() {
        return false;
    }

    @Override
    public boolean isForge() {
        return true;
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isOf(ItemStack stack, Enums.ItemType type) {
        if (Objects.requireNonNull(type) == Enums.ItemType.NetheriteElytra) {
            return stack.getItem() instanceof NetheriteElytraItem;
        }
        return false;
    }

    @Override
    public Item getCustomElytraItem() {
        return new NetheriteElytraItem();
    }

    @Override
    public Item getDurableLanternItem(Item.Settings settings) {
        if(LANTERN_DEFAULT_ITEM == null) {
            LANTERN_DEFAULT_ITEM = new DurableLanternItem(settings);
        }

        return LANTERN_DEFAULT_ITEM;
    }

    @Override
    public Item getUpgradedLanternItem(Item.Settings settings) {
        if(LANTERN_UPGRADED_ITEM == null) {
            LANTERN_UPGRADED_ITEM = new NetheriteLanternItem(settings);
        }

        return LANTERN_UPGRADED_ITEM;
    }

    @Override
    public boolean isLoadedMod(String modName) {
        return false;
    }
}
