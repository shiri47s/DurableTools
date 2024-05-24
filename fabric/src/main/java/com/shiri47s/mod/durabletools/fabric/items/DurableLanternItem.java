package com.shiri47s.mod.durabletools.fabric.items;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class DurableLanternItem extends com.shiri47s.mod.durabletools.items.DurableLanternItem implements TrinketRenderer {

    public DurableLanternItem(Item.Settings settings) {
        super(settings);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!slotReference.inventory().getSlotType().getName().equals("charm")) {
            return;
        }

        if (!(entity instanceof AbstractClientPlayerEntity player)) {
            return;
        }

        TrinketRenderer.translateToChest(matrices, (PlayerEntityModel<AbstractClientPlayerEntity>)contextModel, player);

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrices.translate(-.12, -.15, -.03);

        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 0);
    }
}
