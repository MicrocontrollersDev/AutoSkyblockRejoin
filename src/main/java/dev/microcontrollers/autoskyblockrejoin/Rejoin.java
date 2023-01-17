package dev.microcontrollers.autoskyblockrejoin;

import cc.polyfrost.oneconfig.utils.Multithreading;
import dev.microcontrollers.autoskyblockrejoin.config.RejoinConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.TimeUnit;

public class Rejoin {
    private static final String[] disconnectMessages = {
            "An exception occurred in your connection, so you were put in the SkyBlock Lobby!",
            "Evacuating to Hub...",
            "This server will restart soon: Game Update",
            "Out of sync, check your internet connection!",
            "[Important] This server will restart soon: Scheduled Restart",
            "A disconnect occurred in your connection, so you were put in the SkyBlock Lobby!",
            "You are being transferred to the HUB for being AFK!",
            "You are being transferred to the Prototype Lobby for being AFK!",
            "You are AFK. Move around to return from AFK."
    };

    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        for (String disc : disconnectMessages) {
            if (RejoinConfig.autoSkyblockRejoin && message.equals(disc)) {
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/l"), 30, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock"), 60, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/is"), 105, TimeUnit.SECONDS);
                return;
            }
        }
    }
}
