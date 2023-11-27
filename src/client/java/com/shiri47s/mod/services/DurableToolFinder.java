package com.shiri47s.mod.services;

import com.shiri47s.mod.DurableTools;
import com.shiri47s.mod.tools.DurableFireworkRocketItem;
import com.shiri47s.mod.tools.DurableTotemItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DurableToolFinder {
    public static ItemStack findFireworkRocket(PlayerEntity playerEntity) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            if (itemStack.getItem() instanceof DurableFireworkRocketItem) {
                return itemStack;
            }
        }

        return null;
    }


    public static ItemStack findTotem(PlayerEntity playerEntity) {
        ArrayList<ItemStack> itemStackList = new ArrayList<ItemStack>();
        Hand[] hands = Hand.values();
        TrinketsApi.getTrinketComponent(playerEntity).ifPresent(component -> component.forEach((slotReference, itemStack) -> itemStackList.add(itemStack)));
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            itemStackList.add(itemStack);
        }

        ItemStack foundItemStack = null;
        for (ItemStack itemStack : itemStackList) {
            // Priority will be given to the netherite totem first.
            if (itemStack.isOf(DurableTools.NETHERITE_TOTEM)) {
                return itemStack;
            }

            if (itemStack.isOf(DurableTools.DURABLE_TOTEM)) {
                foundItemStack = itemStack;
            }
        }

        return foundItemStack;
    }

    public static EquipmentSlot getFireworkRocketEquipmentSlot(LivingEntity livingEntity, ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof DurableFireworkRocketItem)) {
            return null;
        }

        return getEquipmentSlot(livingEntity, itemStack);
    }

    @Nullable
    private static EquipmentSlot getEquipmentSlot(LivingEntity livingEntity, ItemStack itemStack) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack testItemStack = livingEntity.getStackInHand(hand);
            if (itemStack.isOf(testItemStack.getItem())) {
                return switch (hand) {
                    case MAIN_HAND -> EquipmentSlot.MAINHAND;
                    case OFF_HAND -> EquipmentSlot.OFFHAND;
                };
            }
        }

        return null;
    }

    public static EquipmentSlot getTotemEquipmentSlot(LivingEntity livingEntity, ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof DurableTotemItem)) {
            return null;
        }

        return getEquipmentSlot(livingEntity, itemStack);
    }
}
