package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.EmbedManager;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.awt.*;

public class onJoin {
    private final EmbedManager embedManager;

    public onJoin(EmbedManager embedManager) {
        this.embedManager = embedManager;
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        // Do something when a player joins
        String playerName = event.player.getName();
        embedManager.sendEmbed(playerName + " has joined the server!", Color.GREEN);
    }
}
