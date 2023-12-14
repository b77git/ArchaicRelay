package com.archaic.ArchaicRelay.Config;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ConfigHandler {
    private Configuration config;
    private Logger logger;

    private static final String DEFAULT_BOT_TOKEN = "your_default_token";
    private static final String[] DEFAULT_CHANNEL_IDS = {"123456789"};
    private static final String DEFAULT_JOIN_MESSAGE = "{user} has joined the server!";
    private static final String DEFAULT_LEAVE_MESSAGE = "{user} has left the server!";
    private static final String DEFAULT_STARTING_MESSAGE = "Server starting...";
    private static final String DEFAULT_STARTED_MESSAGE = "Server started!";
    private static final String DEFAULT_STOPPED_MESSAGE = "Server stopped...";

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

        botToken = config.getString("BotToken", Configuration.CATEGORY_GENERAL, DEFAULT_BOT_TOKEN,
                "The token for your Discord bot");

        gameChannelIds = Arrays.asList(config.getStringList("gameChannelIds", Configuration.CATEGORY_GENERAL,
                DEFAULT_CHANNEL_IDS, "The Channel Ids of where the Discord bot will output the game information"));

        joinMessage = config.getString("JoinMessage", Configuration.CATEGORY_GENERAL, DEFAULT_JOIN_MESSAGE,
                "The message that will be sent when a user joins the server");

        leaveMessage = config.getString("LeaveMessage", Configuration.CATEGORY_GENERAL, DEFAULT_LEAVE_MESSAGE,
                "The message that will be sent when a user leaves the server");

        startingMessage = config.getString("StartingMessage", Configuration.CATEGORY_GENERAL, DEFAULT_STARTING_MESSAGE,
                "The message that will be sent when the server is starting");

        startedMessage = config.getString("StartedMessage", Configuration.CATEGORY_GENERAL, DEFAULT_STARTED_MESSAGE,
                "The message that will be sent when the server starts");

        stoppedMessage = config.getString("StoppedMessage", Configuration.CATEGORY_GENERAL, DEFAULT_STOPPED_MESSAGE,
                "The message that will be sent when a server stops");

        logger.info("Your bot token is " + botToken);

        if (config.hasChanged()) {
            config.save();
        }
    }

    public void saveConfig() {
        logger.info("Saving configuration file...");

        config.get(Configuration.CATEGORY_GENERAL, "BotToken", DEFAULT_BOT_TOKEN).set(botToken);
        config.get(Configuration.CATEGORY_GENERAL, "gameChannelIds", DEFAULT_CHANNEL_IDS).set(gameChannelIds.toArray(new String[0]));
        config.get(Configuration.CATEGORY_GENERAL, "JoinMessage", DEFAULT_JOIN_MESSAGE).set(joinMessage);
        config.get(Configuration.CATEGORY_GENERAL, "LeaveMessage", DEFAULT_LEAVE_MESSAGE).set(leaveMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StartingMessage", DEFAULT_STARTING_MESSAGE).set(startingMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StartedMessage", DEFAULT_STARTED_MESSAGE).set(startedMessage);
        config.get(Configuration.CATEGORY_GENERAL, "StoppedMessage", DEFAULT_STOPPED_MESSAGE).set(stoppedMessage);

        if (config.hasChanged()) {
            config.save();
        }
    }
}