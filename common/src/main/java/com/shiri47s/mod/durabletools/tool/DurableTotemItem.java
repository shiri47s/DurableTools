package com.shiri47s.mod.durabletools.tool;

import com.shiri47s.mod.Durabletools;
import com.shiri47s.mod.durabletools.ModPlatform;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class DurableTotemItem extends DurableToolsToolItem {

    private static final int USE_COST = 60;
    private final ModPlatform platform;

    public DurableTotemItem(ToolMaterial material, Settings settings) {
        super(material, settings);

        platform = Durabletools.getPlatform();
    }

    @Override
    protected Text getAlertText() {
        return null;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_totem.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_totem.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_totem.tooltip_2").formatted(Formatting.GREEN));
    }

    public void trigger(ServerPlayerEntity playerEntity, ItemStack totem) {
        EquipmentSlot slot = platform.getEquipmentSlot(playerEntity, totem);
        if (slot == null) {
            return;
        }

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
        ItemStack itemStack = playerEntity.getMainHandStack();
        Criteria.USED_TOTEM.trigger(playerEntity, itemStack);
        playerEntity.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        totem.damage(USE_COST, playerEntity, slot);

        blessing(playerEntity);
    }

    protected void blessing(PlayerEntity playerEntity) {
        playerEntity.setHealth(2.5F);
        playerEntity.clearStatusEffects();
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        playerEntity.getWorld().sendEntityStatus(playerEntity, (byte) 35);
    }
}
