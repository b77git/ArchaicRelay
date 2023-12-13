package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Map;

public class Bot {
    private final JDA api;
    private final ChannelManager channelManager;
    private final WebhookManager webhookManager;
    private final EmbedManager embedManager;
    StartupMessage startupMessage = new StartupMessage(this);

    public Bot() {
        try {
            ArchaicRelay.getLogger().info("Starting ArchaicRelay bot...");
            String token = ArchaicRelay.getModConfig().botToken;
            this.api = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
            this.webhookManager = new WebhookManager(this, ArchaicRelay.getModConfig().gameChannelIds);
            this.channelManager = new ChannelManager(api, webhookManager);
            this.embedManager = new EmbedManager(this);
            api.awaitReady();

            startupMessage.sendStartMessage();
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

    public EmbedManager getEmbedManager() {
        return embedManager;
    }

    public StartupMessage getStartupMessage() {
        return startupMessage;
    }

}