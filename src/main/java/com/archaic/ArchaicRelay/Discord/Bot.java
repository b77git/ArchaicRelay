package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Icon;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Bot {
    public JDA api;
    public TextChannel textChannel;

    public void startBot(){
        ArchaicRelay.getLogger().info("Starting ArchaicRelay bot...");

        String token = ArchaicRelay.getModConfig().botToken;
        api = JDABuilder.createDefault(token).build();
    }

    private void setTextChannel(){
        int channelId = Integer.parseInt(ArchaicRelay.getModConfig().ChannelId);

        try {
            textChannel = api.awaitReady().getTextChannelById(channelId);
        } catch (InterruptedException e) {
            ArchaicRelay.getLogger().info(e);
        }
    }
}
