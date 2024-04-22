package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.materials.DurableTotemMaterial;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableTotemItem extends DurableToolsItem {

    private static final int ATTACK_DAMAGE = 2;
    private static final float ATTACK_SPEED = -2.8F;

    private static final int USE_COST = 60;

    public DurableTotemItem(Settings settings) {
        this(DurableTotemMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    protected DurableTotemItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public void trigger(ServerPlayerEntity playerEntity, ItemStack totem) {
        playerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
        ItemStack itemStack = playerEntity.getMainHandStack();
        Criteria.USED_TOTEM.trigger(playerEntity, itemStack);
        playerEntity.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);

        totem.damage(USE_COST, playerEntity, e -> {
            EquipmentSlot slot = platform.getEquipmentSlot(playerEntity, totem);
            if (slot != null) {
                e.sendEquipmentBreakStatus(slot);
            } else {
                e.getWorld().sendEntityStatus(e, (byte) 47);
            }
        });

        blessing(playerEntity);
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

    protected void blessing(PlayerEntity playerEntity) {
        playerEntity.setHealth(2.5F);
        playerEntity.clearStatusEffects();
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));
        playerEntity.getWorld().sendEntityStatus(playerEntity, (byte) 35);
    }
}
