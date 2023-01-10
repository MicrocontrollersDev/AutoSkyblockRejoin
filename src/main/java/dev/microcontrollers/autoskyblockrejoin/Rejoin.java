package dev.microcontrollers.autoskyblockrejoin;

import cc.polyfrost.oneconfig.utils.Multithreading;
import dev.microcontrollers.autoskyblockrejoin.config.RejoinConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.TimeUnit;

public class Rejoin {
    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (RejoinConfig.autoSkyblockRejoin && event.message.getUnformattedText().equals("An exception occurred in your connection, so you were put in the SkyBlock Lobby!")) {
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/l"), 10, TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock"), 10, TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/is"), 30, TimeUnit.SECONDS);
        }
    }
}
