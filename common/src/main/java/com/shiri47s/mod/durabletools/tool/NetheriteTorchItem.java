package com.shiri47s.mod.durabletools.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class NetheriteTorchItem extends DurableTorchItem {
    public NetheriteTorchItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_torch.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_torch.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_torch.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    protected int getBurningSecond() {
        return 5;
    }
}
