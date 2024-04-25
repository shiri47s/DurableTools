package com.shiri47s.mod.durabletools;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Consumer;

public class TickListener {

    public void listen (Consumer<PlayerEntity> consumer) {
        TickEvent.SERVER_LEVEL_PRE.register(world -> {
            var players = world.getPlayers();
            players.forEach(consumer);
        });
    }
}
