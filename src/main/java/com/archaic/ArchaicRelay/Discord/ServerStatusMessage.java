package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.ArrayList;
import java.util.List;


public class ServerStatusMessage {
    private final Bot bot;
    private final List<Message> startingMessages;

    public ServerStatusMessage(Bot bot) {
        this.bot = bot;
        this.startingMessages = new ArrayList<>();
    }

    public void sendStartMessage() throws InterruptedException {
        for (TextChannel gameChannel : bot.getChannelManager().getGameChannels()) {
            ArchaicRelay.getLogger().info("Sending starting message to " + gameChannel.getName());
            Message startingMessage = gameChannel.sendMessage(ArchaicRelay.getModConfig().startingMessage).complete();
            startingMessages.add(startingMessage);
        }
    }

    public void editStartMessage(){
        for (Message startingMessage : startingMessages) {
            if (startingMessage != null) {
                startingMessage.editMessage(ArchaicRelay.getModConfig().startedMessage).queue();
            }
        }
    }

    public void sendStopMessage() throws InterruptedException {
        for (TextChannel gameChannel : bot.getChannelManager().getGameChannels()) {
            ArchaicRelay.getLogger().info("Sending stopped message to " + gameChannel.getName());
            Message startingMessage = gameChannel.sendMessage(ArchaicRelay.getModConfig().stoppedMessage).complete();
            startingMessages.add(startingMessage);
        }
    }
}
