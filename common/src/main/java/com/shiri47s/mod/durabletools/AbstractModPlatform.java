package com.shiri47s.mod.durabletools;

import com.shiri47s.mod.durabletools.items.DurableFireworkRocketItem;
import com.shiri47s.mod.durabletools.items.DurableTorchItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.ArrayList;

public abstract class AbstractModPlatform implements IModPlatform {

    @Override
    public ItemStack findItem(PlayerEntity playerEntity, Enums.ItemType type) {
        return switch (type) {
            case DurableFireworkRocket, NetheriteFireworkRocket -> findFireworksRocket(playerEntity);
            case DurableTotem, NetheriteTotem -> findTotem(playerEntity);
            case DurableTorch, NetheriteTorch -> findTorch(playerEntity);
            case NetheriteElytra -> findElytra(playerEntity);
            case DurableLantern, NetheriteLantern -> findLantern(playerEntity);
        };
    }

    @Override
    public EquipmentSlot getEquipmentSlot(LivingEntity livingEntity, ItemStack itemStack) {
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

    protected ItemStack findLantern(PlayerEntity playerEntity) {
        return ItemStack.EMPTY;
    }

    protected ItemStack findFireworksRocket(PlayerEntity playerEntity) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            if (itemStack.getItem() instanceof DurableFireworkRocketItem) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }

    protected ItemStack findTotem(PlayerEntity playerEntity) {
        ArrayList<ItemStack> itemStackList = new ArrayList<>();
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            itemStackList.add(itemStack);
        }

        ItemStack foundItemStack = null;
        for (ItemStack itemStack : itemStackList) {
            // Priority will be given to the netherite totem first.
            if (ItemUtility.isOf(itemStack, Enums.ItemType.NetheriteTotem)) {
                return itemStack;
            }

            if (ItemUtility.isOf(itemStack, Enums.ItemType.DurableTotem)) {
                foundItemStack = itemStack;
            }
        }

        return foundItemStack;
    }

    protected ItemStack findElytra(PlayerEntity playerEntity) {
        ItemStack stack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (ItemUtility.isOf(stack, Enums.ItemType.NetheriteElytra) && ElytraItem.isUsable(stack))
        {
            return stack;
        }

        return ItemStack.EMPTY;
    }

    private ItemStack findTorch(PlayerEntity playerEntity) {
        Hand[] hands = Hand.values();
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            if (itemStack.getItem() instanceof DurableTorchItem) {
                return itemStack;
            }
        }

        return ItemStack.EMPTY;
    }
}
