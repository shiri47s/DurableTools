package com.shiri47s.mod.Equip;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.shiri47s.mod.DurableTools;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class NetheriteElytraItem extends ElytraItem implements FabricElytraItem {

    public NetheriteElytraItem(Settings settings) {
        super(settings);
    }

    public void initialize() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> !DurableTools.NETHERITE_ELYTRA_ITEM.isEquipment(player));
        EntityElytraEvents.CUSTOM.register((LivingEntity entity, boolean tickElytra) -> {
            ItemStack itemStack = getEquipment(entity);
            if (!itemStack.isEmpty()) {
                return DurableTools.NETHERITE_ELYTRA_ITEM.useElytra(entity, itemStack, tickElytra);
            }

            return false;
        });
    }

    public ItemStack getEquipment(LivingEntity entity) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (stack.isOf(DurableTools.NETHERITE_ELYTRA_ITEM) && isUsable(stack))
        {
            return stack;
        }

        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(entity);
        if (optional.isEmpty()) {
            return ItemStack.EMPTY;
        }

        TrinketComponent trinketComponent = optional.get();
        for (Pair<SlotReference, ItemStack> pair : trinketComponent.getEquipped(DurableTools.NETHERITE_ELYTRA_ITEM)) {
            if (pair.getLeft().inventory().getSlotType().getName().equals("cape")) {
                if (pair.getRight().isOf(DurableTools.NETHERITE_ELYTRA_ITEM)) {
                    return pair.getRight();
                }
            }
        }

        return ItemStack.EMPTY;
    }

    public boolean isEquipment(LivingEntity entity) {
        return !getEquipment(entity).isEmpty();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.NETHERITE_INGOT);
    }

    @Override
    public void doVanillaElytraTick(LivingEntity entity, ItemStack chestStack) {
        int nextRoll = entity.getRoll() + 1;

        if (!entity.getWorld().isClient && nextRoll % 10 == 0) {
            if ((nextRoll / 10) % 4 == 0) {
                chestStack.damage(1, entity, p -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
            }

            entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
        }
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        var map = super.getAttributeModifiers(slot);

        if (slot == this.getSlotType()) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            map.forEach(builder::put);

            var armor = new EntityAttributeModifier(UUID.fromString("d680f1ed-ff18-4a87-941a-dade3b331f31"),"Armor", 2.0F, EntityAttributeModifier.Operation.ADDITION);
            var toughness = new EntityAttributeModifier(UUID.fromString("a15a58bd-547d-4e2a-8af5-8e70cefbb570"),"Toughness", 2.0F, EntityAttributeModifier.Operation.ADDITION);
            var speed = new EntityAttributeModifier(UUID.fromString("cac9c98e-3724-4d7a-8b1a-239ad2439cad"),"Speed bonus", 0.10F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            var rocketSpeedBonus = new EntityAttributeModifier(UUID.fromString("9713febd-8a31-4abb-9f33-2e3da530f96e"),"Rocket speed bonus", 0.3F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            builder.put(EntityAttributes.GENERIC_ARMOR, armor);
            builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, toughness);
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed);
            builder.put(DurableTools.GENERIC_FIREWORK_ROCKET_SPEED, rocketSpeedBonus);
            return builder.build();
        }

        return map;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_elytra.tooltip_0").formatted(Formatting.GREEN));
    }

    private boolean useElytra(LivingEntity entity, ItemStack stack, boolean tickElytra) {
        if (!isUsable(stack)) {
            return false;
        }

        if (!tickElytra) {
            return true;
        }

        int nextRoll = entity.getRoll() + 1;
        World world = entity.getWorld();
        if (!world.isClient && nextRoll % 10 == 0) {
            if ((nextRoll / 10) % 2 == 0) {
                stack.damage(1, entity, null);
            }

            entity.emitGameEvent(GameEvent.ELYTRA_GLIDE);
        }

        return true;
    }
}
