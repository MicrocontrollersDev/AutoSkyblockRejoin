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
            "[Important] This server will restart soon: Scheduled Restart"
    };

    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        for (String disc : disconnectMessages) {
            if (RejoinConfig.autoSkyblockRejoin && message.equals(disc)) {
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/l"), 20, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock"), 30, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/is"), 75, TimeUnit.SECONDS);
                return;
            }
        }
    }
}
