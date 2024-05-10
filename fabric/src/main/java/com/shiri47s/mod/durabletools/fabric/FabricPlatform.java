package com.shiri47s.mod.durabletools.fabric;

import com.shiri47s.mod.durabletools.*;
import com.shiri47s.mod.durabletools.fabric.items.DurableLanternItem;
import com.shiri47s.mod.durabletools.fabric.items.NetheriteElytraItem;
import com.shiri47s.mod.durabletools.fabric.items.NetheriteLanternItem;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
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
    public static Item LANTERN_DEFAULT_ITEM;
    public static Item LANTERN_UPGRADED_ITEM;

    @Override
    public boolean isFabric() {
        return true;
    }

    @Override
    public boolean isForge() {
        return false;
    }

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
            return stack.getItem() instanceof com.shiri47s.mod.durabletools.items.NetheriteElytraItem;
        }
        return false;
    }

    @Override
    public Item getCustomElytraItem() {
        return new NetheriteElytraItem();
    }

    @Override
    public Item getDurableLanternItem(Item.Settings settings) {
        if(LANTERN_DEFAULT_ITEM == null) {
            LANTERN_DEFAULT_ITEM = new DurableLanternItem(settings);
        }

        return LANTERN_DEFAULT_ITEM;
    }

    @Override
    public Item getUpgradedLanternItem(Item.Settings settings) {
        if(LANTERN_UPGRADED_ITEM == null) {
            LANTERN_UPGRADED_ITEM = new NetheriteLanternItem(settings);
        }

        return LANTERN_UPGRADED_ITEM;
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

    @Override
    protected ItemStack findLantern(PlayerEntity playerEntity) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(playerEntity);
        if (optional.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack durableLantern = findLantern(optional.get(), Enums.ItemType.DurableLantern);
        if (!durableLantern.isEmpty()) {
            return durableLantern;
        }

        ItemStack netheriteLantern = findLantern(optional.get(), Enums.ItemType.NetheriteLantern);
        if (!netheriteLantern.isEmpty()) {
            return netheriteLantern;
        }

        return ItemStack.EMPTY;
    }

    private ItemStack findLantern(TrinketComponent trinketComponent, Enums.ItemType type) {
        Item lantern = ItemUtility.get(type);
        for (Pair<SlotReference, ItemStack> pair : trinketComponent.getEquipped(lantern)) {
            if (pair.getLeft().inventory().getSlotType().getName().equals("charm")) {
                if (ItemUtility.isOf(pair.getRight(), type)) {
                    return pair.getRight();
                }
            }
        }

        return ItemStack.EMPTY;
    }
}
