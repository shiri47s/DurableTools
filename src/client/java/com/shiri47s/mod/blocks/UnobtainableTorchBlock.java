package com.shiri47s.mod.blocks;

import net.minecraft.block.TorchBlock;
import net.minecraft.particle.ParticleTypes;

public class UnobtainableTorchBlock extends TorchBlock {
    public UnobtainableTorchBlock(Settings settings) {
        super(settings, ParticleTypes.FLAME);
    }
}
