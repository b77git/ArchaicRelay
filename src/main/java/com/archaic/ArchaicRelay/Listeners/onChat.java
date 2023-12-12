package com.archaic.ArchaicRelay.Listeners;

import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onChat {
    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        String playerName = event.getUsername();
        String message = event.getMessage();

        System.out.println(playerName + " said: " + message);
    }
}
