package com.shiri47s.mod.durabletools.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

public class DurableWallTorchBlock extends WallTorchBlock {
    public DurableWallTorchBlock() {
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
