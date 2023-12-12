package com.archaic.ArchaicRelay.Config;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

public class ConfigHandler {
    private Configuration config;
    private Logger logger;

    // Config options
    public String botToken;
    public String ChannelId;

    public ConfigHandler(Configuration config, Logger logger) {
        this.config = config;
        this.logger = logger;

        // Load the configuration file
        config.load();
        loadConfig();
    }

    private void loadConfig() {
        logger.info("Loading configuration file...");

        // Read config values
        botToken = config.getString("BotToken", Configuration.CATEGORY_GENERAL, "your_default_token",
            "The token for your Discord bot");
        ChannelId = config.getString("ChannelId", Configuration.CATEGORY_GENERAL, "123456789",
            "The Channel Id of the Discord bot");

        if (config.hasChanged()) {
            config.save(); // Save changes if any
        }
    }

    public void saveConfig() {
        logger.info("Saving configuration file...");

        // Set values in the configuration
        config.get(Configuration.CATEGORY_GENERAL, "BotToken", "your_default_token").set(botToken);
        config.get(Configuration.CATEGORY_GENERAL, "ChannelId", "123456789").set(ChannelId);

        // Save the configuration
        if (config.hasChanged()) {
            config.save();
        }
    }
}
