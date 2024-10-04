package com.shiri47s.mod.neoforge.durabletools;

import com.shiri47s.mod.durabletools.ModPlatform;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class ForgePlatform implements ModPlatform {
    @Override
    public void initialize() {

    }

    @Override
    public EquipmentSlot getEquipmentSlot(PlayerEntity player, ItemStack stack) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack testItemStack = player.getStackInHand(hand);
            if (stack.isOf(testItemStack.getItem())) {
                return switch (hand) {
                    case MAIN_HAND -> EquipmentSlot.MAINHAND;
                    case OFF_HAND -> EquipmentSlot.OFFHAND;
                };
            }
        }

        var armors = player.getArmorItems();
        for (var armor : armors) {
            if (stack.isOf(armor.getItem())) {
                return player.getPreferredEquipmentSlot(stack);
            }
        }

        return null;
    }

    @Override
    public ItemStack getEquipItemStack(PlayerEntity player, ItemStack stack) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack testItemStack = player.getStackInHand(hand);
            if (stack.isOf(testItemStack.getItem())) {
                return testItemStack;
            }
        }

        var armors = player.getArmorItems();
        for (var armor : armors) {
            if (stack.isOf(armor.getItem())) {
                return armor;
            }
        }

        return null;
    }
}
