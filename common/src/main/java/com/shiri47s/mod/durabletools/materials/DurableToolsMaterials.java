package com.shiri47s.mod.durabletools.materials;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum DurableToolsMaterials implements ToolMaterial {
    LANTERN_DEFAULT (2, 999, 4.5F, 1.5F, 7, () -> Ingredient.ofItems(Items.LANTERN)),
    LANTERN_UPGRADED (3, 1999, 5.5F, 2.0F, 11, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    DurableToolsMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
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
    public int getMiningLevel() {
        return this.miningLevel;
    }
    public int getEnchantability() {
        return this.enchantability;
    }
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
