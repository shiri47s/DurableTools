package com.shiri47s.mod.tools;

import com.shiri47s.mod.services.DurableToolFinder;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.*;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DurableFireworkRocketItem extends PickaxeItem {

    private static final int ATTACK_DAMAGE = 2;
    private static final float ATTACK_SPEED = -2.8F;

    private static final int DAMAGE_ALERT_VALUE = 10;

    public DurableFireworkRocketItem(Settings settings) {
        this(DurableFireworkRocketMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
    }

    public DurableFireworkRocketItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (!world.isClient) {
            ItemStack itemStack = context.getStack();
            Vec3d vec3d = context.getHitPos();
            Direction direction = context.getSide();
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, context.getPlayer(), vec3d.x + (double)direction.getOffsetX() * 0.15, vec3d.y + (double)direction.getOffsetY() * 0.15, vec3d.z + (double)direction.getOffsetZ() * 0.15, itemStack);
            world.spawnEntity(fireworkRocketEntity);

            PlayerEntity user = context.getPlayer();
            if (user != null) {
                itemStack.damage(2, user, e ->
                {
                    EquipmentSlot slot = DurableToolFinder.getFireworkRocketEquipmentSlot(e, itemStack);
                    if (slot != null) {
                        e.sendEquipmentBreakStatus(slot);
                    }
                });

                this.alertAboutBreak(user, itemStack);
            }
        }

        return ActionResult.success(world.isClient);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isFallFlying()) {
            if (!world.isClient) {

                ItemStack itemStack = new ItemStack(this);
                byte duration = getDuration();
                itemStack.getOrCreateSubNbt("Fireworks").putByte("Flight", duration);
                FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, itemStack, user);
                world.spawnEntity(fireworkRocketEntity);

                ItemStack stackInHand = user.getStackInHand(hand);
                if (!user.getAbilities().creativeMode) {
                    stackInHand.damage(2, user, e ->
                            {
                                EquipmentSlot slot = DurableToolFinder.getFireworkRocketEquipmentSlot(e, stackInHand);
                                if (slot != null) {
                                    e.sendEquipmentBreakStatus(slot);
                                }
                            });
                    alertAboutBreak(user, stackInHand);
                }

                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.durabletools.durable_firework_rocket.tooltip_0"));
        tooltip.add(Text.translatable("item.durabletools.durable_firework_rocket.tooltip_1"));
        tooltip.add(Text.translatable("item.durabletools.durable_firework_rocket.tooltip_2").formatted(Formatting.GREEN));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
    }

    protected byte getDuration() {
        return 1;
    }

    private void alertAboutBreak(PlayerEntity user, ItemStack itemStack) {
        var leftValue = itemStack.getMaxDamage() - itemStack.getDamage();
        if (leftValue < DAMAGE_ALERT_VALUE) {
            user.sendMessage(Text.translatable("item.durabletools.firework_rocket.alert"), true);
        }
    }
}
