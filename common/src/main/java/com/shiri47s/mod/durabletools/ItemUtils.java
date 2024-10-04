package com.shiri47s.mod.durabletools;

import com.shiri47s.mod.Durabletools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ItemUtils {
    public static ItemStack getItemIfEquip(PlayerEntity player, Identifier... ids) {
        for (Identifier id : ids) {
            ItemStack stack = new ItemStack(Registries.ITEM.get(id));
            if (stack.isEmpty()) {
                continue;
            }

            ItemStack equipStack = Durabletools.getPlatform().getEquipItemStack(player, stack);
            if (equipStack != null) {
                return equipStack;
            }
        }

        return null;
    }
}
