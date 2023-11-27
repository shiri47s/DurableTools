package com.shiri47s.mod.blocks;

import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

public class UnobtainableWallTorchBlock extends WallTorchBlock {

    public UnobtainableWallTorchBlock(Settings settings) {
        super(settings, ParticleTypes.FLAME);
    }
}
