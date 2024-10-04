package com.shiri47s.mod.durabletools.tool;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class NetheriteFireworkRocket extends DurableFireworkRocketItem {
    private static final int DURATION = 4;
    public NetheriteFireworkRocket(ToolMaterial material, Settings settings) {
        super(material, settings.component(DataComponentTypes.FIREWORKS, new FireworksComponent(DURATION, List.of())));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_firework_rocket.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_firework_rocket.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_netherite_firework_rocket.tooltip_2").formatted(Formatting.GREEN));
    }
}
