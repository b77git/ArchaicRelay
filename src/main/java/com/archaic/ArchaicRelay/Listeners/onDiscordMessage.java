package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class onDiscordMessage extends ListenerAdapter {
    private final Bot bot;

    public onDiscordMessage(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        String messageContent = event.getMessage().getContentRaw();
        String username = event.getAuthor().getName();
        if (!messageContent.isEmpty()) {
            if (isFromGameChannel(event.getMessage().getChannel())) {
                processGameChannelMessage(username, messageContent);
            }
        }
    }

    private boolean isFromGameChannel(MessageChannelUnion channelUnion) {
        return bot.getChannelManager().getGameChannels().contains(channelUnion.asTextChannel());
    }

    private void processGameChannelMessage(String username, String message) {
        PlayerList playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
        if (!playerList.getPlayers().isEmpty()){
            for (EntityPlayerMP player: playerList.getPlayers()){
                player.sendMessage(new TextComponentString("[Discord] " + username + ": " + message));
            }
        }


    }
}