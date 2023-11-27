package com.shiri47s.mod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.particle.*;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class UnobtainableTorchBlock extends TorchBlock {
    public UnobtainableTorchBlock(Settings settings) {
        super(settings, ParticleTypes.FLAME);
    }
}
