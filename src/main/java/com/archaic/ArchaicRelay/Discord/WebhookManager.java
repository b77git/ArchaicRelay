package com.archaic.ArchaicRelay.Discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.entities.Webhook;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebhookManager {
    private final Map<String, String> webhookUrls;
    private Map<String, Webhook> webhooks = new HashMap<>();
    private final Bot bot;
    private final List<String> gameChannelIds;

    public WebhookManager(Bot bot, List<String> gameChannelIds) {
        this.gameChannelIds = gameChannelIds;
        this.webhookUrls = createWebhooks();
        this.bot = bot;
    }

    Map<String, String> createWebhooks() {
        Map<String, String> result = new HashMap<>();

        for (String channelId : gameChannelIds) {
            String cleanedChannelId = channelId.trim().replaceAll(",", "");
            String webhookUrl = result.getOrDefault(cleanedChannelId, getOrCreateWebhook(cleanedChannelId));
            result.put(cleanedChannelId, webhookUrl);
        }

        return result;
    }

    private String getOrCreateWebhook(String channelId) {
        String cleanedChannelId = channelId.trim().replaceAll(",", "");

        if (webhooks.containsKey(cleanedChannelId)) {
            Webhook existingWebhook = webhooks.get(cleanedChannelId);
            ArchaicRelay.getLogger().info("Webhook already exists for channel " + cleanedChannelId + ". URL: " + existingWebhook.getUrl());
            return existingWebhook.getUrl();
        }

        try {
            TextChannel textChannel = bot.getJDA().getTextChannelById(cleanedChannelId);
            if (textChannel != null) {
                Webhook webhook = textChannel.retrieveWebhooks().complete().stream()
                        .filter(existingWebhook -> existingWebhook.getName().equals("ArchaicRelayWebhook-" + textChannel.getName()))
                        .findFirst()
                        .orElseGet(() -> {
                            ArchaicRelay.getLogger().info("Creating new webhook for channel " + cleanedChannelId);
                            return textChannel.createWebhook("ArchaicRelayWebhook-" + textChannel.getName()).complete();
                        });

                webhooks.put(cleanedChannelId, webhook);

                return webhook.getUrl();
            } else {
                ArchaicRelay.getLogger().warn("Game channel with ID " + cleanedChannelId + " not found.");
                return "";
            }
        } catch (Exception e) {
            ArchaicRelay.getLogger().error("Error creating/getting webhook for channel " + cleanedChannelId + ": " + e.getMessage());
            return "";
        }
    }

    public Map<String, String> getWebhookUrls() {
        return webhookUrls;
    }

    public void sendMessage(String message, String name) {
        for (String channelId : gameChannelIds) {
            String cleanedChannelId = channelId.trim().replaceAll(",", "");
            String webhookUrl = webhookUrls.get(cleanedChannelId);
            if (webhookUrl != null) {
                WebhookClient webhookClient = new WebhookClientBuilder(webhookUrl).build();
                WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
                messageBuilder.setAvatarUrl("https://minotar.net/avatar/" + name)
                        .setContent(message)
                        .setUsername(name);
                webhookClient.send(messageBuilder.build());
            }
        }
    }

    public void sendMessage(String message) {
        for (String channelId : gameChannelIds) {
            String cleanedChannelId = channelId.trim().replaceAll(",", "");
            String webhookUrl = webhookUrls.get(cleanedChannelId);
            if (webhookUrl != null) {
                WebhookClient webhookClient = new WebhookClientBuilder(webhookUrl).build();
                WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
                messageBuilder.setAvatarUrl("https://i.postimg.cc/13VFYXDf/pngtree-background-shadow-vector-cartoon-server-png-image-4093223-removebg-preview.png")
                        .setUsername("Server")
                        .setContent(message);
                webhookClient.send(messageBuilder.build());
            }
        }
    }
}