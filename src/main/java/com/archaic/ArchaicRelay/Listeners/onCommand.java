package com.archaic.ArchaicRelay.Listeners;

import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onCommand {
    // Event handler for command events
    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        String commandName = event.getCommand().getName();
        String commandSender = event.getSender().getName();
        String[] commandArgs = event.getParameters();

        // Check if the command is "/say" or "/me"
        if (commandName.equals("say") || commandName.equals("me")) {
            // Do something with the command
            System.out.println(commandSender + " executed /" + commandName + " with args: " + String.join(" ", commandArgs));
        }
    }
}
