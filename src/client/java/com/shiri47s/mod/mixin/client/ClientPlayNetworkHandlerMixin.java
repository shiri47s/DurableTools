package com.shiri47s.mod.mixin.client;

import com.shiri47s.mod.services.DurableToolFinder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "getActiveTotemOfUndying", at = @At(value = "HEAD"), cancellable = true)
    private static void durabletools$getActiveTotemOfUndying(PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack totem = DurableToolFinder.findTotem(player);
        if (totem != null) {
            cir.setReturnValue(totem);
        }
    }
}
