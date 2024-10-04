package com.shiri47s.mod.durabletools.tool;

import com.shiri47s.mod.Durabletools;
import com.shiri47s.mod.durabletools.ModPlatform;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class DurableFireworkRocketItem extends DurableToolsToolItem {
    private static final int USE_COST = 2;

    private final ModPlatform platform;

    public DurableFireworkRocketItem(ToolMaterial material, Settings settings) {
        super(material, settings);

        platform = Durabletools.getPlatform();
    }

    @Override
    protected Text getAlertText() {
        return Text.translatable("item.durabletools.firework_rocket.alert");
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            PlayerEntity player = context.getPlayer();
            if (player == null) {
                return ActionResult.PASS;
            }

            ItemStack itemStack = context.getStack();
            EquipmentSlot slot = platform.getEquipmentSlot(player, itemStack);
            if (slot == null) {
                return ActionResult.PASS;
            }

            Vec3d vec3d = context.getHitPos();
            Direction direction = context.getSide();
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, context.getPlayer(), vec3d.x + (double) direction.getOffsetX() * 0.15, vec3d.y + (double) direction.getOffsetY() * 0.15, vec3d.z + (double) direction.getOffsetZ() * 0.15, itemStack);
            world.spawnEntity(fireworkRocketEntity);

            itemStack.damage(USE_COST, player, slot);
            this.alertAboutBreak(player, itemStack);
        }

        return ActionResult.success(world.isClient);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (player.isFallFlying()) {
            if (!world.isClient) {
                ItemStack itemStack = new ItemStack(this);
                ItemStack stackInHand = player.getStackInHand(hand);
                EquipmentSlot slot = platform.getEquipmentSlot(player, itemStack);
                if (slot != null) {
                    FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, itemStack, player);
                    world.spawnEntity(fireworkRocketEntity);

                    if (!player.getAbilities().creativeMode) {
                        stackInHand.damage(2, player, slot);
                    }

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                }

                alertAboutBreak(player, stackInHand);
            }

            return TypedActionResult.success(player.getStackInHand(hand), world.isClient());
        } else {
            return TypedActionResult.pass(player.getStackInHand(hand));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_firework_rocket.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_firework_rocket.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_firework_rocket.tooltip_2").formatted(Formatting.GREEN));
    }
}
