package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.util.Map;

public class Bot {
    private final JDA api;
    private final ChannelManager channelManager;
    private final WebhookManager webhookManager;
    StartupMessage startupMessage = new StartupMessage(this);

    public Bot() {
        try {
            ArchaicRelay.getLogger().info("Starting ArchaicRelay bot...");
            String token = ArchaicRelay.getModConfig().botToken;
            this.api = JDABuilder.createDefault(token).build();
            this.webhookManager = new WebhookManager(this);
            this.channelManager = new ChannelManager(api, webhookManager);
//            this.embedManager = new EmbedManager(this);
            api.awaitReady();

            for (Map.Entry<String, String> entry : webhookManager.getWebhookUrls().entrySet()) {
                String channelId = entry.getKey();
                String webhookUrl = entry.getValue();

                String message = "Hello from ArchaicRelay!";
                webhookManager.sendMessage(channelId, message);
            }

            //startupMessage.sendStartMessage();
        } catch (InterruptedException e) {
            // Handle the exception as needed
            ArchaicRelay.getLogger().error("Error starting the bot: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve the interrupted status
            throw new RuntimeException(e);
        }
    }

    public JDA getJDA() {
        return api;
    }

    public ChannelManager getChannelManager() {
        return channelManager;
    }

    public WebhookManager getWebhookManager() {
        return webhookManager;
    }

    public StartupMessage getStartupMessage() {
        return startupMessage;
    }

}