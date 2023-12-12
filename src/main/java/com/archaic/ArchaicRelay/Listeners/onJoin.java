package com.archaic.ArchaicRelay.Listeners;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class onJoin {
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        // Do something when a player joins
        String playerName = event.player.getName();
        System.out.println("Player " + playerName + " has joined the server!");
    }
}
