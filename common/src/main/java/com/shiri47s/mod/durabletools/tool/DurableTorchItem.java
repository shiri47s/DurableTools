package com.shiri47s.mod.durabletools.tool;

import com.shiri47s.mod.Durabletools;
import com.shiri47s.mod.durabletools.Const;
import com.shiri47s.mod.durabletools.ModPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableTorchItem extends DurableToolsToolItem {

    private static final int USE_COST = 2;

    private final ModPlatform platform;

    private final Block block;
    private final Block wallBlock;

    public DurableTorchItem(ToolMaterial material, Settings settings) {
        super(material, settings);
        platform = Durabletools.getPlatform();
        block = Registries.BLOCK.get(Identifier.of(Const.MOD_ID, Const.Block.TORCH));
        wallBlock = Registries.BLOCK.get(Identifier.of(Const.MOD_ID, Const.Block.WALL_TORCH));
    }

    @Override
    protected Text getAlertText() {
        return null;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.postHit(stack, target, attacker);

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
        target.setOnFireFor(getBurningSecond());

        return result;
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

            BlockState testState = this.getPlacementState(new ItemPlacementContext(context));
            if (testState == null) {
                return ActionResult.PASS;
            }

            Direction side = context.getSide();
            BlockPos blockPos = context.getBlockPos();
            BlockPos hitPos = blockPos.offset(side);
            BlockState blockState = world.getBlockState(hitPos);
            if (blockState.isAir()) {
                if (!world.canSetBlock(hitPos)) {
                    return ActionResult.PASS;
                }

                if (!context.getWorld().canPlace(blockState, blockPos, ShapeContext.absent())) {
                    return ActionResult.PASS;
                }

                BlockState placementState;
                if (side == Direction.UP || side == Direction.DOWN) {
                    placementState = this.block.getDefaultState();
                }
                else {
                    placementState = this.wallBlock.getPlacementState(new ItemPlacementContext(context));
                }

                world.setBlockState(hitPos, placementState);
                world.playSound(null, hitPos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS);
                stack.damage(USE_COST, player, slot);

                return ActionResult.success(true);
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.durabletools.dt_torch.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.dt_torch.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.dt_torch.tooltip_2").formatted(Formatting.GREEN));
    }

    @SuppressWarnings("ReassignedVariable")
    @Nullable
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState sideState = this.wallBlock.getPlacementState(context);
        BlockState replacementState = null;
        WorldView worldView = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        Direction[] directions = context.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction != Direction.DOWN.getOpposite()) {
                BlockState hisState = direction == Direction.DOWN ? this.block.getPlacementState(context) : sideState;
                if (hisState != null && hisState.canPlaceAt(worldView, blockPos)) {
                    replacementState = hisState;
                    break;
                }
            }
        }

        return replacementState != null && worldView.canPlace(replacementState, blockPos, ShapeContext.absent()) ? replacementState : null;
    }

    protected int getBurningSecond() {
        return 3;
    }
}
