package com.shiri47s.mod.durabletools.materials;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum DurableToolsToolMaterials implements ToolMaterial {
    FIREWORK_ROCKET(456, 4.0F, 1.0F, 8, () -> Ingredient.ofItems(Items.FIREWORK_ROCKET), BlockTags.INCORRECT_FOR_IRON_TOOL),
    NETHERITE_FIREWORK_ROCKET(999, 7.0F, 2.0F, 16, () -> Ingredient.ofItems(Items.NETHERITE_INGOT), BlockTags.INCORRECT_FOR_NETHERITE_TOOL),

    TOTEM(361, 3.0F, 0.0F, 9, () -> Ingredient.ofItems(Items.TOTEM_OF_UNDYING), BlockTags.INCORRECT_FOR_IRON_TOOL),
    NETHERITE_TOTEM(999, 5.0F, 2.0F, 18, () -> Ingredient.ofItems(Items.NETHERITE_INGOT), BlockTags.INCORRECT_FOR_NETHERITE_TOOL),

    TORCH(255, 5.0F, 2.0F, 10, () -> Ingredient.ofItems(Items.TORCH), BlockTags.INCORRECT_FOR_IRON_TOOL),
    NETHERITE_TORCH(999, 9.0F, 3.0F, 20, () -> Ingredient.ofItems(Items.NETHERITE_INGOT), BlockTags.INCORRECT_FOR_NETHERITE_TOOL);

    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredientSupplier;
    private final TagKey<Block> inverseTag;

    DurableToolsToolMaterials(int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient, TagKey<Block> inverseTag) {
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredientSupplier = repairIngredient;
        this.inverseTag = inverseTag;
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }
}
