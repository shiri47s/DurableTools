package com.shiri47s.mod.mixin.client;

import com.shiri47s.mod.DurableTools;
import com.shiri47s.mod.RenderingContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends ProjectileEntity {

    @Shadow private @Nullable LivingEntity shooter;

    public FireworkRocketEntityMixin(EntityType<? extends FireworkRocketEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "tick", at = @At("STORE"), ordinal = 1)
    private Vec3d durabletools$crabVec3D(Vec3d vec) {
        if (this.shooter == null) return vec;
        var speedModifier = this.shooter.getAttributeValue(DurableTools.GENERIC_FIREWORK_ROCKET_SPEED);

        if (speedModifier == 0) return vec.multiply(0);
        return vec.multiply(1 / speedModifier);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 0))
    private Vec3d durabletools$increaseRocketSpeed(Vec3d velocity, double x, double y, double z) {
        if (this.shooter == null) return velocity;
        var speedModifier = this.shooter.getAttributeValue(DurableTools.GENERIC_FIREWORK_ROCKET_SPEED);

        return velocity.multiply(speedModifier).add(x, y, z);
    }

    @Inject( method = "tick", at = @At(value = "HEAD"))
    public void durabletools$tickHEAD(CallbackInfo ci) {
        RenderingContext.fireworkRocketShooter = this.shooter;
    }

    @Inject( method = "tick", at = @At(value = "RETURN"))
    public void durabletools$tickRETURN(CallbackInfo ci) {
        RenderingContext.fireworkRocketShooter = null;
    }
}