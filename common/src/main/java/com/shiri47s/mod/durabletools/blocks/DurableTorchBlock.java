package com.shiri47s.mod.durabletools.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class DurableTorchBlock extends TorchBlock {
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
