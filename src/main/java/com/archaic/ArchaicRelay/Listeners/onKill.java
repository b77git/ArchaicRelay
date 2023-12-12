package com.archaic.ArchaicRelay.Listeners;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onKill {
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer killedPlayer = (EntityPlayer) event.getEntityLiving();

            // Check if there's a killer
            EntityLivingBase killer = event.getSource().getTrueSource() instanceof EntityLivingBase
                    ? (EntityLivingBase) event.getSource().getTrueSource()
                    : null;

            // Do something with the killed player and the killer
            String killedPlayerName = killedPlayer.getName();
            String killerName = killer != null ? killer.getName() : "Unknown";

            System.out.println("Player " + killedPlayerName + " has been killed by " + killerName + "!");
        }
    }
}
