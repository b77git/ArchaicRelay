package com.archaic.ArchaicRelay.Listeners;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class onLeave {
    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        // Do something when a player leaves
        String playerName = event.player.getName();
        System.out.println("Player " + playerName + " has left the server!");
    }
}

