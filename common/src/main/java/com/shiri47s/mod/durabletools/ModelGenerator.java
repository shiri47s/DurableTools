package com.shiri47s.mod.durabletools;

import com.shiri47s.mod.durabletools.block.DurableTorchBlock;
import com.shiri47s.mod.durabletools.block.DurableWallTorchBlock;
import com.shiri47s.mod.durabletools.materials.DurableToolsToolMaterials;
import com.shiri47s.mod.durabletools.tool.*;
import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

public class ModelGenerator {
    private static final DeferredRegister<Item> ARMOR_REGISTER = DeferredRegister.create(Const.MOD_ID, RegistryKeys.ITEM);
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(Const.MOD_ID, RegistryKeys.BLOCK);
    private static final DeferredRegister<Item> TOOL_REGISTER = DeferredRegister.create(Const.MOD_ID, RegistryKeys.ITEM);

    public static void Generate() {
        generateBlocks();
        generateTools();
    }

    private static void generateBlocks() {
        BLOCK_REGISTER.register(Const.Block.TORCH, DurableTorchBlock::new);
        BLOCK_REGISTER.register(Const.Block.WALL_TORCH, DurableWallTorchBlock::new);

        BLOCK_REGISTER.register();
    }

    private static void generateTools() {
        TOOL_REGISTER.register(Const.Tool.FIREWORK_ROCKET, () -> new DurableFireworkRocketItem(DurableToolsToolMaterials.FIREWORK_ROCKET, (new Item.Settings()).rarity(Rarity.UNCOMMON).attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.FIREWORK_ROCKET, 1.0F, -2.8F))));
        TOOL_REGISTER.register(Const.Tool.NETHERITE_FIREWORK_ROCKET, () -> new NetheriteFireworkRocket(DurableToolsToolMaterials.NETHERITE_FIREWORK_ROCKET, (new Item.Settings()).rarity(Rarity.RARE).fireproof().attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.NETHERITE_FIREWORK_ROCKET, 1.0F, -2.4F))));

        TOOL_REGISTER.register(Const.Tool.TOTEM, () -> new DurableTotemItem(DurableToolsToolMaterials.TOTEM, (new Item.Settings()).rarity(Rarity.UNCOMMON).attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.TOTEM, 1.0F, -2.8F))));
        TOOL_REGISTER.register(Const.Tool.NETHERITE_TOTEM, () -> new NetheriteTotemItem(DurableToolsToolMaterials.NETHERITE_TOTEM, (new Item.Settings()).rarity(Rarity.RARE).fireproof().attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.NETHERITE_TOTEM, 1.0F, -2.4F))));

        TOOL_REGISTER.register(Const.Tool.TORCH, () -> new DurableTorchItem(DurableToolsToolMaterials.TORCH, (new Item.Settings()).attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.TORCH, 1.0F, -2.8F))));
        TOOL_REGISTER.register(Const.Tool.NETHERITE_TORCH, () -> new NetheriteTorchItem(DurableToolsToolMaterials.NETHERITE_TORCH, (new Item.Settings()).rarity(Rarity.RARE).fireproof().attributeModifiers(DurableToolsToolItem.createAttributeModifiers(DurableToolsToolMaterials.NETHERITE_TORCH, 1.0F, -2.4F))));

        TOOL_REGISTER.register();
    }
}
