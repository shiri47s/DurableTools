package com.shiri47s.mod.durabletools;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class BlockUtility {
    public static Block get(Enums.BlockType type) {
        return switch (type) {
            case TorchBlock -> Registries.BLOCK.get(new Identifier(Constants.MOD_ID, Constants.Torch.BLOCK_ID));
            case WallTorchBlock -> Registries.BLOCK.get(new Identifier(Constants.MOD_ID, Constants.Torch.WALL_BLOCK_ID));
        };
    }
}
