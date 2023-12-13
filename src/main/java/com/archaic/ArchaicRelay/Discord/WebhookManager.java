package com.archaic.ArchaicRelay.Discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebhookManager {
    private final Map<String, String> webhookUrls;
    private final Bot bot;
    private final List<TextChannel> gameChannels;

    public WebhookManager(Bot bot){
        this.webhookUrls = createWebhooks();
        this.bot = bot;
        this.gameChannels = bot.getChannelManager().getGameChannels();
    }

    Map<String, String> createWebhooks() {
        Map<String, String> result = new HashMap<>();

        for (TextChannel gameChannel : gameChannels) {
            String webhookUrl = result.getOrDefault(gameChannel.getId(), createWebhook(gameChannel));
            result.put(gameChannel.getId(), webhookUrl);
        }

        return result;
    }

    public String createWebhook(TextChannel textChannel) {
        try {
            return textChannel.createWebhook("ArchaicRelayWebhook-" + textChannel.getName()).complete().getUrl();
        } catch (Exception e) {
            ArchaicRelay.getLogger().error("Error creating webhook for channel " + textChannel.getId() + ": " + e.getMessage());
            return "";
        }
    }

    public Map<String, String> getWebhookUrls() {
        return webhookUrls;
    }

    // You can add a method to send a message using a specific webhook URL
    public void sendMessage(String channelId, String message, String name) {
        String webhookUrl = webhookUrls.get(channelId);
        if (webhookUrl != null) {
            WebhookClient webhookClient = new WebhookClientBuilder(webhookUrl).build();
            WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
            messageBuilder.setAvatarUrl("https://minotar.net/avatar/Khajiitos")
                    .setContent(message)
                    .setUsername(name);
            webhookClient.send(messageBuilder.build());
        }
    }

    public void sendMessage(String channelId, String message) {
        String webhookUrl = webhookUrls.get(channelId);
        if (webhookUrl != null) {
            WebhookClient webhookClient = new WebhookClientBuilder(webhookUrl).build();
            WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
            messageBuilder.setAvatarUrl("https://minotar.net/avatar/Khajiitos")
                    .setContent(message);
            webhookClient.send(messageBuilder.build());
        }
    }
}
