package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.materials.NetheriteTotemMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetheriteTotemItem extends DurableTotemItem {

    private static final int ATTACK_DAMAGE = 3;
    private static final float ATTACK_SPEED = -2.6F;

    public NetheriteTotemItem(Settings settings) {
        super(NetheriteTotemMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
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

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_totem.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.netherite_totem.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.netherite_totem.tooltip_2").formatted(Formatting.GREEN));
    }
}
