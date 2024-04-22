package com.shiri47s.mod.durabletools;

import com.shiri47s.mod.durabletools.blocks.DurableTorchBlock;
import com.shiri47s.mod.durabletools.blocks.DurableWallTorchBlock;
import com.shiri47s.mod.durabletools.items.*;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemModelGenerator {
    private static final DeferredRegister<Item> ItemRegister = DeferredRegister.create(Constants.MOD_ID, RegistryKeys.ITEM);
    private static final DeferredRegister<Block> blockRegister = DeferredRegister.create(Constants.MOD_ID, RegistryKeys.BLOCK);
    private static final DeferredRegister<ItemGroup> groupRegister = DeferredRegister.create(Constants.MOD_ID, RegistryKeys.ITEM_GROUP);

    public static void generate(IModPlatform platform) {
        ItemGroup itemGroup = generateGroups(platform);

        generateItems(platform, itemGroup);
        generateBlocks(platform, itemGroup);
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void generateItems(IModPlatform platform, ItemGroup itemGroup) {
        ItemRegister.register(Constants.FireworkRocket.ITEM_ID, () -> new DurableFireworkRocketItem(new Item.Settings().arch$tab(itemGroup)));
        ItemRegister.register(Constants.FireworkRocket.UPGRADED_ID, () -> new NetheriteFireworkRocketItem(new Item.Settings().arch$tab(itemGroup)));

        ItemRegister.register(Constants.Totem.ITEM_ID, () -> new DurableTotemItem(new Item.Settings().arch$tab(itemGroup)));
        ItemRegister.register(Constants.Totem.UPGRADED_ID, () -> new NetheriteTotemItem(new Item.Settings().arch$tab(itemGroup)));

        ItemRegister.register(Constants.Torch.ITEM_ID, () -> new DurableTorchItem(new Item.Settings().arch$tab(itemGroup)));
        ItemRegister.register(Constants.Torch.UPGRADED_ID, () -> new NetheriteTorchItem(new Item.Settings().arch$tab(itemGroup)));

        ItemRegister.register(Constants.Elytra.ITEM_ID, platform::getCustomElytraItem);

        ItemRegister.register();
    }

    private static void generateBlocks(IModPlatform platform, ItemGroup itemGroup) {
        blockRegister.register(Constants.Torch.BLOCK_ID, DurableTorchBlock::new);
        blockRegister.register(Constants.Torch.WALL_BLOCK_ID, DurableWallTorchBlock::new);

        blockRegister.register();
    }

    public static ItemGroup generateGroups(IModPlatform platform) {
        groupRegister.register(Constants.GROUP_ID, () -> CreativeTabRegistry.create(
                Text.translatable("item.durabletools.group"),
                () -> new ItemStack(Items.TOTEM_OF_UNDYING)));

        groupRegister.register();

        return Registries.ITEM_GROUP.get(new Identifier(Constants.MOD_ID, Constants.GROUP_ID));
    }
}
