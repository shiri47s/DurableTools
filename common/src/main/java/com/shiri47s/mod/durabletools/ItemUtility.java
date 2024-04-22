package com.shiri47s.mod.durabletools;

import com.shiri47s.mod.durabletools.items.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;


public class ItemUtility {

    public static boolean isOf(ItemStack itemStack, Enums.ItemType type) {
        if (itemStack == null || itemStack.isEmpty()) {
            return false;
        }

        return switch (type) {
            case DurableFireworkRocket -> itemStack.getItem() instanceof DurableFireworkRocketItem;
            case NetheriteFireworkRocket -> itemStack.getItem() instanceof NetheriteFireworkRocketItem;
            case DurableTotem -> itemStack.getItem() instanceof DurableTotemItem;
            case NetheriteTotem -> itemStack.getItem() instanceof NetheriteTotemItem;
            case DurableTorch -> itemStack.getItem() instanceof DurableTorchItem;
            case NetheriteTorch -> itemStack.getItem() instanceof NetheriteTorchItem;
            case NetheriteElytra -> DurableTools.getPlatform().isOf(itemStack, Enums.ItemType.NetheriteElytra);
        };
    }

    public static Item get(Enums.ItemType type) {
        return switch (type) {
            case DurableFireworkRocket -> Registries.ITEM.get(new Identifier(Constants.MOD_ID, Constants.FireworkRocket.ITEM_ID));
            case NetheriteFireworkRocket -> Registries.ITEM.get(new Identifier(Constants.MOD_ID, Constants.FireworkRocket.UPGRADED_ID));
            case DurableTotem -> Registries.ITEM.get(new Identifier(Constants.MOD_ID, Constants.Totem.ITEM_ID));
            case NetheriteTotem -> Registries.ITEM.get(new Identifier(Constants.MOD_ID, Constants.Totem.UPGRADED_ID));
            case NetheriteElytra -> Registries.ITEM.get(new Identifier(Constants.MOD_ID, Constants.Elytra.ITEM_ID));
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
