package com.shiri47s.mod.durabletools.mixin;

import com.shiri47s.mod.durabletools.DurableTools;
import com.shiri47s.mod.durabletools.Enums;
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
        ItemStack totem = DurableTools.getPlatform().findItem(player, Enums.ItemType.DurableTotem);
        if (totem != null) {
            cir.setReturnValue(totem);
        }
    }
}
