package com.shiri47s.mod.durabletools;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ModPlatform {

    void initialize();

    EquipmentSlot getEquipmentSlot(PlayerEntity player, ItemStack stack);

    ItemStack getEquipItemStack(PlayerEntity player, ItemStack stack);
}
