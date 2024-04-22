package com.shiri47s.mod.durabletools.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.shiri47s.mod.durabletools.DurableTools;
import com.shiri47s.mod.durabletools.IModPlatform;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class NetheriteElytraItem extends ElytraItem {

    public static final EntityAttribute GENERIC_FIREWORK_ROCKET_SPEED = new ClampedEntityAttribute("generic.firework_rocket_speed", 1.0f, 0.0, 1024.0f).setTracked(true);

    protected final IModPlatform platform;
    public NetheriteElytraItem() {
        super(new Item.Settings().rarity(Rarity.EPIC).maxDamage(999));
        platform = DurableTools.getPlatform();
        init();
    }

    protected void init() { }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.NETHERITE_INGOT);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        var map = super.getAttributeModifiers(slot);

        if (slot == this.getSlotType()) {
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            map.forEach(builder::put);

            var armor = new EntityAttributeModifier(UUID.fromString("0680f1ed-ff18-4a87-941a-dade3b331f31"),"Armor", 2.0F, EntityAttributeModifier.Operation.ADDITION);
            var toughness = new EntityAttributeModifier(UUID.fromString("115a58bd-547d-4e2a-8af5-8e70cefbb570"),"Toughness", 1.0F, EntityAttributeModifier.Operation.ADDITION);
            var speed = new EntityAttributeModifier(UUID.fromString("2ac9c98e-3724-4d7a-8b1a-239ad2439cad"),"Speed bonus", 0.10F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            var rocketSpeedBonus = new EntityAttributeModifier(UUID.fromString("3713febd-8a31-4abb-9f33-2e3da530f96e"),"Rocket speed bonus", 0.3F, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
            builder.put(EntityAttributes.GENERIC_ARMOR, armor);
            builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, toughness);
            builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed);
            //builder.put(GENERIC_FIREWORK_ROCKET_SPEED, rocketSpeedBonus);
            return builder.build();
        }

        return map;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_elytra.tooltip_0").formatted(Formatting.GREEN));
    }
}
