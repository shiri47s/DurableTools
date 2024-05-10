package com.shiri47s.mod.durabletools;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IModPlatform {
    boolean isFabric();
    boolean isForge();

    void initialize();

    boolean isOf(ItemStack stack, Enums.ItemType type);

    ItemStack findItem(PlayerEntity playerEntity, Enums.ItemType type);

    EquipmentSlot getEquipmentSlot(LivingEntity livingEntity, ItemStack itemStack);

    Item getCustomElytraItem();

    Item getDurableLanternItem(Item.Settings setting);

    Item getUpgradedLanternItem(Item.Settings setting);

    boolean isLoadedMod(String modName);
}
