package com.shiri47s.mod.durabletools.tool;

import com.shiri47s.mod.Durabletools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;

public abstract class DurableToolsToolItem extends PickaxeItem {

    private static final int ALERT_DURABILITY = 15;

    @SuppressWarnings("UnstableApiUsage")
    public DurableToolsToolItem(ToolMaterial material, Settings settings) {
        super(material, settings.arch$tab(Durabletools.TAB_SUPPLIER));
    }

    protected void alertAboutBreak(PlayerEntity user, ItemStack itemStack) {
        var leftValue = itemStack.getMaxDamage() - itemStack.getDamage();
        if (leftValue < ALERT_DURABILITY) {
            user.sendMessage(getAlertText(), true);
        }
    }

    protected abstract Text getAlertText();
}
