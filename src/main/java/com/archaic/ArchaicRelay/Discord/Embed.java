package com.archaic.ArchaicRelay.Discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import scala.actors.MQueue;

import java.awt.*;

public class Embed {
    Bot bot = new Bot();
    JDA api = bot.api;

    public void sendConsoleEmbed(){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.green);

        sendEmbed(embed);
    }

    public void sendJoinEmbed(String playername){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.green);
        setEmbedAvatar(playername, embed);

        sendEmbed(embed);
    }

    public void sendLeaveEmbed(String playername){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.red);
        setEmbedAvatar(playername, embed);

        sendEmbed(embed);
    }

    public void sendDeathEmbed(String playername){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.red);
        setEmbedAvatar(playername, embed);

        sendEmbed(embed);
    }

    public void sendEmbed(EmbedBuilder embed){
        bot.textChannel.sendMessageEmbeds(embed.build()).queue();
    }

    public void setEmbedAvatar(String name, EmbedBuilder  embed){
        String url = "https://minotar.net/avatar/" + name;
        embed.setAuthor(name, url);
    }
}
