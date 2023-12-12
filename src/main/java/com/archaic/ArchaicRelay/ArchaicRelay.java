package com.archaic.ArchaicRelay;

import com.archaic.ArchaicRelay.Config.ConfigHandler;
import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Listeners.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = ArchaicRelay.MODID, name = ArchaicRelay.MODNAME, version = ArchaicRelay.MODVERSION, dependencies = "required-after:forge@[11.16.0.1865,)", useMetadata = true)
public class ArchaicRelay {
    public static final String MODID = "archaicrelay";
    public static final String MODNAME = "Archaic Relay";
    public static final String MODVERSION = "0.0.1";

    private static ConfigHandler configHandler;
    private static Logger logger = LogManager.getLogger(ArchaicRelay.MODID);

    private Bot bot = new Bot();


    public ArchaicRelay(){
    }

    @EventHandler
    private void onServerStarting(FMLServerStartingEvent event) {
        File configDir = new File(event.getServer().getDataDirectory(), "config");
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        File configFile = new File(configDir, "ArchaicRelay.cfg");
        Configuration config = new Configuration(configFile);
        configHandler = new ConfigHandler(config, logger);

        MinecraftForge.EVENT_BUS.register(new onChat());
        MinecraftForge.EVENT_BUS.register(new onCommand());
        MinecraftForge.EVENT_BUS.register(new onDeath());
        MinecraftForge.EVENT_BUS.register(new onJoin());
        MinecraftForge.EVENT_BUS.register(new onLeave());
        MinecraftForge.EVENT_BUS.register(new onKill());

        bot.startBot();


    }

    public static ConfigHandler getModConfig() {
        return configHandler;
    }

    public static Logger getLogger(){
        return logger;
    }

}
