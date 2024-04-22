package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.materials.NetheriteTorchMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetheriteTorchItem extends DurableTorchItem {

    private static final int ATTACK_DAMAGE = 3;
    private static final float ATTACK_SPEED = -2.6F;

    public NetheriteTorchItem(Settings settings) {
        super(NetheriteTorchMaterial.INSTANCE, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.netherite_torch.tooltip_2").formatted(Formatting.GREEN));
    }
}
