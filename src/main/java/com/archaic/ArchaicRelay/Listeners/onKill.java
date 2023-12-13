package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onKill {
    private final WebhookManager webhookManager;

    public onKill(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer killedPlayer = (EntityPlayer) event.getEntityLiving();

            EntityLivingBase killer = event.getSource().getTrueSource() instanceof EntityLivingBase
                    ? (EntityLivingBase) event.getSource().getTrueSource()
                    : null;

            String killedPlayerName = killedPlayer.getName();
            String killerName = killer != null ? killer.getName() : "Unknown";
            if (!killerName.equals("Unknown")) {
                webhookManager.sendMessage("Player " + killedPlayerName + " has been killed by " + killerName + "!");
            } else {
                webhookManager.sendMessage("Player " + killedPlayerName + " has died!");
            }
        }
    }
}
