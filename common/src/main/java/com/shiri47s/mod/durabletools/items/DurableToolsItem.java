package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.DurableTools;
import com.shiri47s.mod.durabletools.IModPlatform;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class DurableToolsItem extends PickaxeItem {
    protected IModPlatform platform;

    public DurableToolsItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        platform = DurableTools.getPlatform();
    }
}
