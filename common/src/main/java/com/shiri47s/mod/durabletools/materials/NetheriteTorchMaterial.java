package com.shiri47s.mod.durabletools.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class NetheriteTorchMaterial implements ToolMaterial {
    public static final NetheriteTorchMaterial INSTANCE = new NetheriteTorchMaterial();

    @Override
    public int getDurability() {
        return 567;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 2.5F;
    }

    @Override
    public float getAttackDamage() {
        return 2.5F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHERITE_INGOT);
    }
}
