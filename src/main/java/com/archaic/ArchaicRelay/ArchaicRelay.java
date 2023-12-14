package com.archaic.ArchaicRelay;

import com.archaic.ArchaicRelay.Config.ConfigHandler;
import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.EmbedManager;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import com.archaic.ArchaicRelay.Listeners.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


@Mod(modid = ArchaicRelay.MODID, name = ArchaicRelay.MODNAME, version = ArchaicRelay.MODVERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class ArchaicRelay {
    public static final String MODID = "archaicrelay";
    public static final String MODNAME = "Archaic Relay";
    public static final String MODVERSION = "0.0.1";

    private static ConfigHandler configHandler;
    private static final Logger logger = LogManager.getLogger(ArchaicRelay.MODID);

    private Bot bot;

    public ArchaicRelay(){
    }

    @EventHandler
    private void onServerStarting(FMLServerStartingEvent event) {
        logger.info("onServerStarting called");
        File configDir = new File(event.getServer().getDataDirectory(), "config");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        File configFile = new File(configDir, "ArchaicRelay.cfg");
        Configuration config = new Configuration(configFile);

        logger.info("Config directory: " + configDir.getAbsolutePath());
        logger.info("Config file: " + configFile.getAbsolutePath());
        configHandler = new ConfigHandler(config, logger);

        if (config.hasChanged()) {
            logger.info("Config has changed; saving...");
            config.save();
            logger.info("Config saved.");
        } else {
            logger.info("No changes detected in the configuration.");
        }

        logger.info("Bot token: " + configHandler.botToken);
        logger.info("Game channel IDs: " + configHandler.gameChannelIds);

        bot = new Bot();
        if (bot.getJDA() != null) {
            bot.getJDA().addEventListener(new onDiscordMessage(bot));
            WebhookManager webhookManager = bot.getWebhookManager();
            EmbedManager embedManager = bot.getEmbedManager();
            MinecraftForge.EVENT_BUS.register(new onChat(webhookManager));
            MinecraftForge.EVENT_BUS.register(new onCommand(webhookManager));
            MinecraftForge.EVENT_BUS.register(new onDeath(webhookManager));
            MinecraftForge.EVENT_BUS.register(new onJoin(embedManager));
            MinecraftForge.EVENT_BUS.register(new onLeave(embedManager));
            MinecraftForge.EVENT_BUS.register(new onKill(webhookManager));
        }
    }

    @EventHandler
    private void onServerStop(FMLServerStoppedEvent event){
        configHandler.saveConfig();
    }

    @EventHandler
    private void onServerStart(FMLServerStartedEvent event){
        bot.getStartupMessage().editStartMessage();
    }

    public static ConfigHandler getModConfig() {
        return configHandler;
    }

    public static Logger getLogger(){
        return logger;
    }

}
