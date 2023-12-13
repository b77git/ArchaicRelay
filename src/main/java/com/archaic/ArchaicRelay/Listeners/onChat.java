package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onChat {
    private final WebhookManager webhookManager;

    public onChat(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }

    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        String playerName = event.getUsername();
        String message = event.getMessage();
        webhookManager.sendMessage(message, playerName);
    }
}
