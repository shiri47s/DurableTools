package com.shiri47s.mod.durabletools.mixin;

import com.shiri47s.mod.durabletools.DurableTools;
import com.shiri47s.mod.durabletools.Enums;
import com.shiri47s.mod.durabletools.items.DurableTotemItem;
import com.shiri47s.mod.durabletools.items.NetheriteElytraItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @SuppressWarnings("UnreachableCode")
    @Inject(method = "tryUseTotem", at = @At(value = "HEAD"), cancellable = true)
    private void durabletools$tryUseTotem(DamageSource src, CallbackInfoReturnable<Boolean> cir) {
        if (!((LivingEntity)(Object)this instanceof PlayerEntity))
        {
            return;
        }

        LivingEntity livingEntity = (LivingEntity)(Object)this;
        if (!(livingEntity instanceof ServerPlayerEntity playerEntity)) {
            return;
        }

        ItemStack totem = DurableTools.getPlatform().findItem(playerEntity, Enums.ItemType.DurableTotem);
        if (totem == null) {
            return;
        }

        if (!(totem.getItem() instanceof DurableTotemItem item)) {
            return;
        }

        item.trigger(playerEntity, totem);

        cir.setReturnValue(true);
    }

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void additionalEntityAttributes$addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(NetheriteElytraItem.GENERIC_FIREWORK_ROCKET_SPEED);
    }
}