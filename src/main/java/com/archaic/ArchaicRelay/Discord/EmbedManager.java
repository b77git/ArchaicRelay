package com.archaic.ArchaicRelay.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.List;

public class EmbedManager {
    private final Bot bot;
    private final List<TextChannel> gameChannels;

    public EmbedManager(Bot bot){
        this.bot = bot;
        this.gameChannels = bot.getChannelManager().getGameChannels();
    }

    public void sendEmbed(String message, Color color){
        EmbedBuilder embed = new EmbedBuilder();
        embed.addField("", message, true);
        embed.setColor(color);
        for (TextChannel gameChannel : gameChannels){
            gameChannel.sendMessageEmbeds(embed.build()).queue();
        }
    }

}
