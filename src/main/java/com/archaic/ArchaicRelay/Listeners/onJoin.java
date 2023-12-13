package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class onJoin {
    private final WebhookManager webhookManager;

    public onJoin(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        // Do something when a player joins
        String playerName = event.player.getName();
//        bot.getEmbedManager().sendJoinEmbed(playerName);
    }
}
