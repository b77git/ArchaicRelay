package com.archaic.ArchaicRelay.Listeners;

import com.archaic.ArchaicRelay.Discord.WebhookManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onDeath {
    private final WebhookManager webhookManager;

    public onDeath(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }

    @SubscribeEvent
    public void onLivingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof net.minecraft.entity.player.EntityPlayer) {
            net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer) event.getEntityLiving();
        }
    }
}
