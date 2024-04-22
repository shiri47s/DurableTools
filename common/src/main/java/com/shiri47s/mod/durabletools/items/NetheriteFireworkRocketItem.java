package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.materials.NetheriteFireworkRocketMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NetheriteFireworkRocketItem extends DurableFireworkRocketItem {

    private static final int ATTACK_DAMAGE = 3;
    private static final float ATTACK_SPEED = -2.6F;

    public NetheriteFireworkRocketItem(Settings settings) {
        super(NetheriteFireworkRocketMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.netherite_firework_rocket.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.netherite_firework_rocket.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.netherite_firework_rocket.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }

    @Override
    protected byte getDuration() {
        return 4;
    }
}
