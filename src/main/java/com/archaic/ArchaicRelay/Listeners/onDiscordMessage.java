package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import ibxm.Player;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class onDiscordMessage extends ListenerAdapter {
    private final Bot bot;

    public onDiscordMessage(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Ignore messages from bots and webhooks
        if (event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        // Check if the message is not empty
        String messageContent = event.getMessage().getContentRaw();
        String username = event.getAuthor().getName();
        if (!messageContent.isEmpty()) {
            // Check if the message is from one of the game channels
            if (isFromGameChannel(event.getMessage().getChannel())) {
                // Process the message as needed
                processGameChannelMessage(username, messageContent);
            }
        }
    }

    private boolean isFromGameChannel(MessageChannelUnion channelUnion) {
        // Check if the channel is a TextChannel and if it's one of the game channels
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