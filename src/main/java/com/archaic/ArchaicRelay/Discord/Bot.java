package com.archaic.ArchaicRelay.Discord;

import com.archaic.ArchaicRelay.ArchaicRelay;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
    private JDA api;
    private ChannelManager channelManager;
    private WebhookManager webhookManager;
    private EmbedManager embedManager;
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
        } catch (InvalidTokenException e) {
            ArchaicRelay.getLogger().error("Invalid bot token: " + e.getMessage());

        } catch (InterruptedException e) {
            ArchaicRelay.getLogger().error("Error starting the bot: " + e.getMessage());
            Thread.currentThread().interrupt();
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