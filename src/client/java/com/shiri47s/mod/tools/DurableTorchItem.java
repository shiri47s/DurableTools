package com.shiri47s.mod.tools;

import com.shiri47s.mod.DurableTools;
import com.shiri47s.mod.services.DurableToolFinder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableTorchItem extends PickaxeItem {

    private static final int ATTACK_DAMAGE = 2;
    private static final float ATTACK_SPEED = -2.8F;

    private static final int DAMAGE_ALERT_VALUE = 10;

    private static final int USE_COST = 2;

    public DurableTorchItem(Settings settings) {
        this(DurableTorchMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    public DurableTorchItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            ItemStack placementTorch = new ItemStack(DurableTools.UNOBTAINABLE_TORCH_BLOCK_ITEM);
            ItemUsageContext placementContext =
                    new ItemUsageContext(
                            context.getWorld(),
                            context.getPlayer(),
                            context.getHand(),
                            placementTorch,
                            new BlockHitResult(
                                    context.getHitPos(),
                                    context.getSide(),
                                    context.getBlockPos(),
                                    context.hitsInsideBlock()));

            ActionResult actionResult = placementTorch.getItem().useOnBlock(placementContext);
            if (actionResult == ActionResult.CONSUME) {
                world.playSound(
                        null,
                        context.getBlockPos(),
                        SoundEvents.BLOCK_WOOD_PLACE,
                        SoundCategory.BLOCKS,
                        1.0f,
                        1.0f);
                PlayerEntity user = context.getPlayer();
                if (user != null) {
                    ItemStack itemStack = context.getStack();
                    itemStack.damage(USE_COST, user, e ->
                    {
                        EquipmentSlot slot = DurableToolFinder.getFireworkRocketEquipmentSlot(e, itemStack);
                        if (slot != null) {
                            e.sendEquipmentBreakStatus(slot);
                        }

                        alertAboutBreak(user, itemStack);
                    });

                    return ActionResult.success(false);
                }
            }
        }

        return ActionResult.PASS;
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

    private void alertAboutBreak(PlayerEntity user, ItemStack itemStack) {
        var leftValue = itemStack.getMaxDamage() - itemStack.getDamage();
        if (leftValue < DAMAGE_ALERT_VALUE) {
            user.sendMessage(Text.translatable("item.durabletools.durable_torch.alert"), true);
        }
    }
}
