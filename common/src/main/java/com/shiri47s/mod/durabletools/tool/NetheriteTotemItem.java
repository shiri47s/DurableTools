package com.shiri47s.mod.durabletools.tool;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class NetheriteTotemItem extends DurableTotemItem {
    public NetheriteTotemItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_totem.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_totem.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_totem.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    public void blessing(PlayerEntity playerEntity) {
        playerEntity.setHealth(6.5F);
        playerEntity.clearStatusEffects();
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 300, 2));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 1500, 0));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1000, 1));

        playerEntity.getWorld().sendEntityStatus(playerEntity, (byte) 35);
    }
}
