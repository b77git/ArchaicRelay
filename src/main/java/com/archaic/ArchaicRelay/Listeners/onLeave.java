package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.EmbedManager;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.awt.*;

public class onLeave {
    private final EmbedManager embedManager;

    public onLeave(EmbedManager embedManager) {
        this.embedManager = embedManager;
    }

    @SubscribeEvent
    public void onPlayerLeave(PlayerEvent.PlayerLoggedOutEvent event) {
        String playerName = event.player.getName();
        embedManager.sendEmbed(playerName + " has left the server!", Color.RED);
    }
}

