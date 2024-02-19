package com.shiri47s.mod;

import com.shiri47s.mod.Equip.NetheriteElytraItem;
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
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
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

    public static final NetheriteElytraItem NETHERITE_ELYTRA_ITEM = new NetheriteElytraItem(new FabricItemSettings().rarity(Rarity.EPIC).maxDamage(999));
    public static final EntityAttribute GENERIC_FIREWORK_ROCKET_SPEED = new ClampedEntityAttribute(Constants.FIREWORK_ROCKET_SPEED_ATTRIBUTE_ID, 1.0f, 0.0, 1024.0f).setTracked(true);

    public void initialize() {
        Registry.register(Registries.ATTRIBUTE, new Identifier(Constants.NAMESPACE, Constants.FIREWORK_ROCKET_SPEED_ATTRIBUTE_ID), GENERIC_FIREWORK_ROCKET_SPEED);
        Registry.register(Registries.ITEM, new Identifier(Constants.NAMESPACE, Constants.NETHERITE_ELYTRA_ID), NETHERITE_ELYTRA_ITEM);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.addAfter(Items.FIREWORK_ROCKET, DURABLE_FIREWORK_ROCKET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.addAfter(DURABLE_FIREWORK_ROCKET, NETHERITE_FIREWORK_ROCKET));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.TOTEM_OF_UNDYING, DURABLE_TOTEM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(DURABLE_TOTEM, NETHERITE_TOTEM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.addAfter(Items.ELYTRA, NETHERITE_ELYTRA_ITEM));

        NETHERITE_ELYTRA_ITEM.initialize();
    }
}
