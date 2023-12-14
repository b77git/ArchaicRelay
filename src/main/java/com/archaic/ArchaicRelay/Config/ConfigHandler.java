package com.archaic.ArchaicRelay.Config;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ConfigHandler {
    private Configuration config;
    private Logger logger;

    // Config options
    public String botToken;
    public List<String> gameChannelIds;
    public String joinMessage;
    public String leaveMessage;
    public String startingMessage;
    public String startedMessage;
    public String stoppedMessage;

    public ConfigHandler(Configuration config, Logger logger) {
        this.config = config;
        this.logger = logger;

        config.load();
        loadConfig();
    }

    private void loadConfig() {
        logger.info("Loading configuration file...");

        // Read config values
        botToken = config.getString("BotToken", Configuration.CATEGORY_GENERAL, "your_default_token",
                "The token for your Discord bot");

        gameChannelIds = Arrays.asList(config.getStringList("gameChannelIds", Configuration.CATEGORY_GENERAL,
                new String[]{"123456789"}, "The Channel Ids of where the Discord bot will output the game information"));

        joinMessage = config.getString("JoinMessage", Configuration.CATEGORY_GENERAL, "{user} has joined the server!",
                "The message that will be send when a user joins the server");

        leaveMessage = config.getString("LeaveMessage", Configuration.CATEGORY_GENERAL, "{user} has left the server!",
                "The message that will be send when a user leaves the server");

        startingMessage = config.getString("StartingMessage", Configuration.CATEGORY_GENERAL, "Server starting...",
                "The message that will be send when the server is starting");

        startedMessage = config.getString("StartedMessage", Configuration.CATEGORY_GENERAL, "Server started!",
                "The message that will be send when the server starts");

        stoppedMessage = config.getString("StoppedMessage", Configuration.CATEGORY_GENERAL, "Server stopped...",
                "The message that will be send when a server stops");

        logger.info("Your bot token is " + botToken);

        if (config.hasChanged()) {
            config.save(); // Save changes if any
        }
    }

    public void saveConfig() {
        logger.info("Saving configuration file...");

        // Set values in the configuration
        config.get(Configuration.CATEGORY_GENERAL, "BotToken", "your_default_token").set(botToken);
        config.get(Configuration.CATEGORY_GENERAL, "gameChannelIds", new String[]{"123456789"}).set(gameChannelIds.toArray(new String[0]));
        config.get(Configuration.CATEGORY_GENERAL, "JoinMessage", "{user} has joined the server!").set(joinMessage);
        config.get(Configuration.CATEGORY_GENERAL, "LeaveMessage", "{user} has left the server!").set(leaveMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StartingMessage", "Server starting...").set(joinMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StartedMessage", "Server started!").set(leaveMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StoppedMessage", "Server stopped...").set(joinMessage);

        // Save the configuration
        if (config.hasChanged()) {
            config.save();
        }
    }
}