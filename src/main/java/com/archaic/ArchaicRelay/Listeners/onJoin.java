package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.ArchaicRelay;
import com.archaic.ArchaicRelay.Discord.EmbedManager;
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
        String playerName = event.player.getName();
        String message = ArchaicRelay.getModConfig().joinMessage.replaceAll("\\{user}", playerName);
        embedManager.sendEmbed(message, Color.GREEN);
    }
}
