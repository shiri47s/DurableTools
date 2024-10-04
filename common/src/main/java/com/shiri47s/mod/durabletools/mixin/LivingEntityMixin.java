package com.shiri47s.mod.durabletools.mixin;

import com.shiri47s.mod.durabletools.Const;
import com.shiri47s.mod.durabletools.ItemUtils;
import com.shiri47s.mod.durabletools.tool.DurableTotemItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
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
        if (!(livingEntity instanceof ServerPlayerEntity player)) {
            return;
        }

        ItemStack stack = ItemUtils.getItemIfEquip(
                player,
                Identifier.of(Const.MOD_ID, Const.Tool.NETHERITE_TOTEM),
                Identifier.of(Const.MOD_ID, Const.Tool.TOTEM));
        if (stack != null && stack.getItem() instanceof DurableTotemItem totem)
        {
            totem.trigger(player, stack);
            cir.setReturnValue(true);
        }
    }
}