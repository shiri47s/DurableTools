package com.shiri47s.mod.mixin.client;

import com.shiri47s.mod.services.DurableToolFinder;
import com.shiri47s.mod.tools.DurableTotemItem;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "tryUseTotem", at = @At(value = "HEAD"), cancellable = true)
    private void durabletools$tryUseTotem(DamageSource src, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (!(livingEntity instanceof ServerPlayerEntity playerEntity)) {
            return;
        }

        ItemStack totem = DurableToolFinder.findTotem(playerEntity);
        if (totem == null) {
            return;
        }

        if (!(totem.getItem() instanceof DurableTotemItem item)) {
            return;
        }

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
        ItemStack itemStack = playerEntity.getMainHandStack();
        Criteria.USED_TOTEM.trigger(playerEntity, itemStack);
        playerEntity.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);

        totem.damage(50, playerEntity, e ->  {
            EquipmentSlot slot = DurableToolFinder.getTotemEquipmentSlot(playerEntity, totem);
            if (slot != null) {
                e.sendEquipmentBreakStatus(slot);
            }
            else {
                e.getWorld().sendEntityStatus(e, (byte) 47);
            }
        });

        item.blessing(playerEntity);

        cir.setReturnValue(true);
    }

}