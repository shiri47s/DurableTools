package com.shiri47s.mod.durabletools.fabric.items;

import com.shiri47s.mod.durabletools.Enums;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRenderEvents;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;

public class NetheriteElytraItem extends com.shiri47s.mod.durabletools.items.NetheriteElytraItem implements FabricElytraItem {

    @Override
    public void init() {
        LivingEntityFeatureRenderEvents.ALLOW_CAPE_RENDER.register(player -> !isEquipment(player));
        EntityElytraEvents.CUSTOM.register((LivingEntity entity, boolean tickElytra) -> {
            if (!(entity instanceof PlayerEntity player)) {
                return false;
            }

            ItemStack itemStack = platform.findItem(player, Enums.ItemType.NetheriteElytra);
            if (itemStack.getItem() instanceof NetheriteElytraItem elytra) {
                return elytra.useElytra(entity, itemStack, tickElytra);
            }

            return false;
        });
    }

    private boolean isEquipment(PlayerEntity player) {
        if (platform.findItem(player, Enums.ItemType.NetheriteElytra) != ItemStack.EMPTY)
        {
            return true;
        }

        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(player);
        return optional.isPresent();
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
