package com.shiri47s.mod.durabletools.fabric;

import com.shiri47s.mod.durabletools.*;
import com.shiri47s.mod.durabletools.fabric.items.FabricNetheriteElytra;
import com.shiri47s.mod.durabletools.items.NetheriteElytraItem;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class FabricPlatform extends AbstractModPlatform {
    @Override
    public void initialize() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockUtility.get(Enums.BlockType.TorchBlock), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockUtility.get(Enums.BlockType.WallTorchBlock), RenderLayer.getCutout());
    }

    @Override
    protected ItemStack findTotem(PlayerEntity playerEntity) {
        ArrayList<ItemStack> itemStackList = new ArrayList<ItemStack>();
        Hand[] hands = Hand.values();
        TrinketsApi.getTrinketComponent(playerEntity).ifPresent(component -> component.forEach((slotReference, itemStack) -> itemStackList.add(itemStack)));
        for (Hand hand : hands) {
            ItemStack itemStack = playerEntity.getStackInHand(hand);
            itemStackList.add(itemStack);
        }

        ItemStack foundItemStack = null;
        for (ItemStack itemStack : itemStackList) {
            // Priority will be given to the netherite totem first.
            if (ItemUtility.isOf(itemStack, Enums.ItemType.NetheriteTotem)) {
                return itemStack;
            }

            if (ItemUtility.isOf(itemStack, Enums.ItemType.DurableTotem)) {
                foundItemStack = itemStack;
            }
        }

        return foundItemStack;
    }

    @Override
    public boolean isOf(ItemStack stack, Enums.ItemType type) {
        if (Objects.requireNonNull(type) == Enums.ItemType.NetheriteElytra) {
            return stack.getItem() instanceof NetheriteElytraItem;
        }
        return false;
    }

    @Override
    public Item getCustomElytraItem() {
        return new FabricNetheriteElytra();
    }

    @Override
    public boolean isLoadedMod(String modName) {
        return FabricLoaderImpl.INSTANCE.isModLoaded(modName);
    }

    @Override
    protected ItemStack findElytra(PlayerEntity playerEntity) {
        ItemStack stack = playerEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (ItemUtility.isOf(stack, Enums.ItemType.NetheriteElytra))
        {
            return stack;
        }

        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(playerEntity);
        if (optional.isEmpty()) {
            return ItemStack.EMPTY;
        }

        Item elytra = ItemUtility.get(Enums.ItemType.NetheriteElytra);
        TrinketComponent trinketComponent = optional.get();
        for (Pair<SlotReference, ItemStack> pair : trinketComponent.getEquipped(elytra)) {
            if (pair.getLeft().inventory().getSlotType().getName().equals("cape")) {
                if (ItemUtility.isOf(pair.getRight(), Enums.ItemType.NetheriteElytra)) {
                    return pair.getRight();
                }
            }
        }

        return ItemStack.EMPTY;
    }
}
