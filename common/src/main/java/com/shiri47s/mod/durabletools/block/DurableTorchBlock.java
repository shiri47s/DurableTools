package com.shiri47s.mod.durabletools.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class DurableTorchBlock extends net.minecraft.block.TorchBlock {
    public DurableTorchBlock() {
        super(
                ParticleTypes.FLAME,
                AbstractBlock
                        .Settings
                        .create()
                        .noCollision()
                        .breakInstantly()
                        .luminance(arg -> 14)
                        .sounds(BlockSoundGroup.WOOD)
                        .pistonBehavior(PistonBehavior.DESTROY));
    }
}
