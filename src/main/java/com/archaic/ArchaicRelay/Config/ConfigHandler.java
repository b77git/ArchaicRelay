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

        // Save the configuration
        if (config.hasChanged()) {
            config.save();
        }
    }
}