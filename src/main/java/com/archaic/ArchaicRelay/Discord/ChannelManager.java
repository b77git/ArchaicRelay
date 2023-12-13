package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.*;

public class ChannelManager {
    private final JDA api;
    private List<TextChannel> gameChannels;
    private final WebhookManager webhookManager;

    public ChannelManager(JDA api, WebhookManager webhookManager) {
        this.api = api;
        this.gameChannels = new ArrayList<>();
        this.webhookManager = webhookManager;
        setGameChannels();
    }

    private void setGameChannels() {
        try {
            api.awaitReady();

            Set<String> gameChannelStrings = new HashSet<>(ArchaicRelay.getModConfig().gameChannelIds);
            List<TextChannel> channels = new ArrayList<>();

            for (String rawChannelId : gameChannelStrings) {
                String cleanedChannelId = rawChannelId.trim().replaceAll(",", "");

                if (!cleanedChannelId.isEmpty()) {
                    TextChannel channel = api.getTextChannelById(cleanedChannelId);
                    if (channel != null) {
                        channels.add(channel);
                    } else {
                        ArchaicRelay.getLogger().warn("Game channel with ID " + cleanedChannelId + " not found.");
                    }
                }
            }

            synchronized (this) {
                gameChannels = channels;
                webhookManager.getWebhookUrls().clear();
                webhookManager.getWebhookUrls().putAll(webhookManager.createWebhooks());
            }
        } catch (InterruptedException e) {
            ArchaicRelay.getLogger().error("Error setting game channels: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }


    public List<TextChannel> getGameChannels() {
        return gameChannels;
    }
}
