package com.shiri47s.mod.durabletools.fabric.mixin;

import com.google.common.collect.Iterables;
import com.shiri47s.mod.durabletools.fabric.items.DurableLanternItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "getArmorItems", require = 1, allow = 1, at = @At("RETURN"), cancellable = true)
    private void durabletools$getArmorItems(CallbackInfoReturnable<Iterable<ItemStack>> cir) {
        if (!((Entity)(Object)this instanceof PlayerEntity playerEntity))
        {
            return;
        }

        ArrayList<ItemStack> trinketsItemsList = new ArrayList<ItemStack>();
        TrinketsApi.getTrinketComponent(playerEntity).ifPresent(component -> component.forEach((slotReference, itemStack) -> trinketsItemsList.add(itemStack)));
        if (trinketsItemsList.isEmpty()) {
            return;
        }

        boolean hasLanternOnTrinketsSlot = false;
        for (ItemStack itemStack : trinketsItemsList) {
            if (itemStack.getItem() instanceof DurableLanternItem) {
                hasLanternOnTrinketsSlot = true;
            }
        }

        if (!hasLanternOnTrinketsSlot) {
            return;
        }

        Iterable<ItemStack> defaultValue = cir.getReturnValue();
        cir.setReturnValue(Iterables.concat(defaultValue, trinketsItemsList));
    }
}
