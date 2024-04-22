package com.shiri47s.mod.durabletools.items;

import com.shiri47s.mod.durabletools.BlockUtility;
import com.shiri47s.mod.durabletools.Enums;
import com.shiri47s.mod.durabletools.ItemUtility;
import com.shiri47s.mod.durabletools.materials.DurableTorchMaterial;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableTorchItem extends DurableToolsItem {
    private static final int ATTACK_DAMAGE = 2;
    private static final float ATTACK_SPEED = -2.5F;

    private static final int USE_COST = 2;

    public DurableTorchItem(Settings settings) {
        this(DurableTorchMaterial.INSTANCE, settings);
    }

    public DurableTorchItem(ToolMaterial material, Settings settings) {
        super(material, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            PlayerEntity player = context.getPlayer();
            if (player == null) {
                return ActionResult.PASS;
            }

            ItemStack stack = context.getStack();
            EquipmentSlot slot = platform.getEquipmentSlot(player, stack);
            if (slot == null) {
                return ActionResult.PASS;
            }

            if (context.getHand() == Hand.MAIN_HAND) {
                var offStack = player.getStackInHand(Hand.OFF_HAND);
                if (!offStack.isEmpty() && offStack.isStackable()) {
                    return ActionResult.PASS;
                }
            }

            Direction side = context.getSide();
            BlockPos blockPos = context.getBlockPos();
            BlockPos hitPos = blockPos.offset(side);
            BlockState blockState = world.getBlockState(hitPos);
            if (blockState.isAir()) {
                if (!world.canSetBlock(hitPos)) {
                    return ActionResult.PASS;
                }

                BlockState placementState;
                if (side == Direction.UP || side == Direction.DOWN) {
                    placementState = BlockUtility.get(Enums.BlockType.TorchBlock).getDefaultState();
                }
                else {
                    placementState = BlockUtility.get(Enums.BlockType.WallTorchBlock).getPlacementState(new ItemPlacementContext(context));
                }

                world.setBlockState(hitPos, placementState);
                stack.damage(USE_COST, player, e -> {
                    if (!ItemUtility.isOf(stack, Enums.ItemType.DurableTorch)) {
                        return;
                    }

                    EquipmentSlot damageSlot = platform.getEquipmentSlot(e, stack);
                    if (damageSlot != null) {
                        e.sendEquipmentBreakStatus(damageSlot);
                    }
                });

                return ActionResult.success(true);
            }
        }

        return ActionResult.PASS;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.isMobOrPlayer()) {
            return false;
        }

        if (target.isFireImmune()) {
            return false;
        }

        if (target.isWet()) {
            return false;
        }

        target.setOnFire(true);
        target.setOnFireFor(3);
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.durable_torch.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.durable_torch.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.durable_torch.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }
}
