package com.shiri47s.mod.durabletools.mixin;

import com.shiri47s.mod.durabletools.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject( method = "tickMovement", at = @At(value = "HEAD"))
    public void durabletools$tickMovement(CallbackInfo ci) {
        if (!((Entity)(Object)this instanceof PlayerEntity playerEntity))
        {
            return;
        }

        IModPlatform platform = DurableTools.getPlatform();
        ItemStack lantern = platform.findItem(playerEntity, Enums.ItemType.DurableLantern);
        if (lantern.isEmpty()) {
            return;
        }

        if (playerEntity.getWorld().getTime() % 5 != 0) {
            return;
        }

        lantern.damage(Constants.Lantern.COST, playerEntity, e -> {
            if (!ItemUtility.isOf(lantern, Enums.ItemType.DurableTorch)) {
                return;
            }

            EquipmentSlot damageSlot = platform.getEquipmentSlot(e, lantern);
            if (damageSlot != null) {
                e.sendEquipmentBreakStatus(damageSlot);
            }
        });
    }
}
