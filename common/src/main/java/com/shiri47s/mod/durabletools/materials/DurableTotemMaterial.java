package com.shiri47s.mod.durabletools.materials;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DurableTotemMaterial implements ToolMaterial {

    public static final DurableTotemMaterial INSTANCE = new DurableTotemMaterial();

    @Override
    public int getDurability() {
        return 128;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 2.5F;
    }

    @Override
    public float getAttackDamage() {
        return 1.0F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.TOTEM_OF_UNDYING);
    }
}
