package com.shiri47s.mod.tools;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableTotemItem extends PickaxeItem {

    private static final int ATTACK_DAMAGE = 2;
    private static final float ATTACK_SPEED = -2.8F;

    public DurableTotemItem(Settings settings) {
        this(DurableTotemMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    public DurableTotemItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public void blessing(PlayerEntity playerEntity) {
        playerEntity.setHealth(2.5F);
        playerEntity.clearStatusEffects();
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        playerEntity.getWorld().sendEntityStatus(playerEntity, (byte) 35);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.durable_totem.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.durable_totem.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.durable_totem.tooltip_2").formatted(Formatting.GREEN));
    }
}
