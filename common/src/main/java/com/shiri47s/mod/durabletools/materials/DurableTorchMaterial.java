package com.shiri47s.mod.durabletools.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DurableTorchMaterial implements ToolMaterial {
    public static final DurableTorchMaterial INSTANCE = new DurableTorchMaterial();

    @Override
    public int getDurability() {
        return 234;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1.5F;
    }

    @Override
    public float getAttackDamage() {
        return 1.5F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 11;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.TORCH);
    }
}
