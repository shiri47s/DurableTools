package com.shiri47s.mod;

import com.shiri47s.mod.blocks.UnobtainableTorchBlock;
import com.shiri47s.mod.blocks.UnobtainableWallTorchBlock;
import com.shiri47s.mod.tools.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class DurableTools {

    public static final DurableTools INSTANCE = new DurableTools();

    public static final Item DURABLE_FIREWORK_ROCKET =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.DURABLE_FIREWORK_ROCKET_ID), new DurableFireworkRocketItem(new FabricItemSettings()));
    public static final Item NETHERITE_FIREWORK_ROCKET =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.NETHERITE_FIREWORK_ROCKET_ID), new NetheriteFireworkRocketItem(new FabricItemSettings()));

    public static final Item DURABLE_TOTEM =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.DURABLE_TOTEM_ID), new DurableTotemItem(new FabricItemSettings()));

    public static final Item NETHERITE_TOTEM =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.NETHERITE_TOTEM_ID), new NetheriteTotemItem(new FabricItemSettings()));

    public static final Item DURABLE_TORCH =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.DURABLE_TORCH_ID), new DurableTorchItem(new FabricItemSettings()));

    public static final Item NETHERITE_TORCH =
            Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.NETHERITE_TORCH_ID), new NetheriteTorchItem(new FabricItemSettings()));

    public static final Block UNOBTAINABLE_TORCH_BLOCK = new UnobtainableTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block UNOBTAINABLE_WALL_TORCH_BLOCK = new UnobtainableWallTorchBlock(AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 14).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY));

    public static final Item UNOBTAINABLE_TORCH_BLOCK_ITEM = new VerticallyAttachableBlockItem(UNOBTAINABLE_TORCH_BLOCK, UNOBTAINABLE_WALL_TORCH_BLOCK, new Item.Settings(), Direction.DOWN);

    public void initialize() {
        Registry.register(Registries.BLOCK, new Identifier(Constants.NAMESPACE, Constants.UNOBTAINABLE_TORCH_BLOCK_ID), UNOBTAINABLE_TORCH_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier(Constants.NAMESPACE, Constants.UNOBTAINABLE_WALL_TORCH_BLOCK_ID), UNOBTAINABLE_WALL_TORCH_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.UNOBTAINABLE_TORCH_BLOCK_ID), UNOBTAINABLE_TORCH_BLOCK_ITEM);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.addAfter(Items.FIREWORK_ROCKET, DURABLE_FIREWORK_ROCKET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.addAfter(DURABLE_FIREWORK_ROCKET, NETHERITE_FIREWORK_ROCKET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.TOTEM_OF_UNDYING, DURABLE_TOTEM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(DURABLE_TOTEM, NETHERITE_TOTEM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.addAfter(Items.TORCH, DURABLE_TORCH));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> content.addAfter(DURABLE_TORCH, NETHERITE_TORCH));

        setRenderType(UNOBTAINABLE_TORCH_BLOCK);
        setRenderType(UNOBTAINABLE_WALL_TORCH_BLOCK);
    }

    @Environment(EnvType.CLIENT)
    private void setRenderType(Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
    }
}
