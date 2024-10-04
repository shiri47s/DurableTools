package com.shiri47s.mod.fabric.durabletools;

import com.shiri47s.mod.durabletools.Const;
import com.shiri47s.mod.durabletools.ModPlatform;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class FabricPlatform implements ModPlatform {
    @Override
    public void initialize() {
        BlockRenderLayerMap.INSTANCE.putBlock(Registries.BLOCK.get(Identifier.of(Const.MOD_ID, Const.Block.TORCH)), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Registries.BLOCK.get(Identifier.of(Const.MOD_ID, Const.Block.WALL_TORCH)), RenderLayer.getCutout());
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
