package com.archaic.ArchaicRelay.Listeners;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class onDeath {
    @SubscribeEvent
    public void onLivingDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof net.minecraft.entity.player.EntityPlayer) {
            // A player has died
            net.minecraft.entity.player.EntityPlayer player = (net.minecraft.entity.player.EntityPlayer) event.getEntityLiving();
            // Do something with the player's death
            System.out.println("Player " + player.getName() + " has died!");
        }
    }
}
