package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.materials.DurableToolsMaterials;
import net.minecraft.item.ToolMaterial;

public class DurableLanternItem extends DurableToolsItem {
    public static final int ATTACK_DAMAGE = 1;
    public static final float ATTACK_SPEED = 1.5F;
    public DurableLanternItem(Settings settings) {
        super(DurableToolsMaterials.LANTERN_DEFAULT, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    protected DurableLanternItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
