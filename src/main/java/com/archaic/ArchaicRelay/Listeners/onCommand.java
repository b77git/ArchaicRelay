package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.Bot;
import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onCommand {
    private final WebhookManager webhookManager;

    public onCommand(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }
    // Event handler for command events
    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        String commandName = event.getCommand().getName();
        String commandSender = event.getSender().getName();
        String[] commandArgs = event.getParameters();

        // Check if the command is "/say" or "/me"
        if (commandName.equals("say") || commandName.equals("me")) {
            // Do something with the command
            String commandOutput = String.join(" ", commandArgs);
            webhookManager.sendMessage(commandOutput);
        }
    }
}
