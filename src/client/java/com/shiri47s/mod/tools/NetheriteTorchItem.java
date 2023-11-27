package com.shiri47s.mod.tools;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetheriteTorchItem extends DurableTorchItem {

    private static final int ATTACK_DAMAGE = 4;
    private static final float ATTACK_SPEED = -2.4F;

    public NetheriteTorchItem(Settings settings) {
        super(NetheriteTorchMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }
}
