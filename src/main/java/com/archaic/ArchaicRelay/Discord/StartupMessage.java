package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.ArrayList;
import java.util.List;


public class StartupMessage {
    private final Bot bot;
    private final List<Message> startingMessages;

    public StartupMessage(Bot bot) {
        this.bot = bot;
        this.startingMessages = new ArrayList<>();
    }

    public void sendStartMessage() throws InterruptedException {
        for (TextChannel gameChannel : bot.getChannelManager().getGameChannels()) {
            ArchaicRelay.getLogger().info("Sending starting message to " + gameChannel.getName());
            Message startingMessage = gameChannel.sendMessage("Server Starting...").complete();
            startingMessages.add(startingMessage);
        }
    }

    public void editStartMessage(){
        for (Message startingMessage : startingMessages) {
            // Ensure that startingMessage is not null before attempting to edit
            if (startingMessage != null) {
                startingMessage.editMessage("Server Started!").queue();
            }
        }
    }
}
