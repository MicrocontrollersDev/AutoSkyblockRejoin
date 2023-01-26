package dev.microcontrollers.autoskyblockrejoin;

import cc.polyfrost.oneconfig.utils.Multithreading;
import cc.polyfrost.oneconfig.utils.Notifications;
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import dev.microcontrollers.autoskyblockrejoin.config.RejoinConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;
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
            "You are AFK. Move around to return from AFK.",
            "You were spawned in Limbo.",
            "/limbo for more information."
    };
    public boolean warpAttempt = false;
    public boolean retry = false;
    public boolean isFree = true;

    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        for (String disc : disconnectMessages) {
            if (RejoinConfig.autoSkyblockRejoin && HypixelUtils.INSTANCE.isHypixel() && message.equals(disc)) {
                if (isFree) warpAttempt = true;
                break;
            }
        }
        if ((warpAttempt || retry) && RejoinConfig.autoSkyblockRejoin && HypixelUtils.INSTANCE.isHypixel()) {
            warpAttempt = false;
            isFree = false;
            if (!retry) Notifications.INSTANCE.send("AutoSkyblockRejoin", "Forced disconnect detected. Attempting to rejoin Skyblock. This will take around 2 minutes.");
            retry = false;
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/l"), (new Random().nextInt(35 - 25) + 25), TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock"), (new Random().nextInt(70 - 50) + 50), TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/is"), (new Random().nextInt(110 - 100) + 100), TimeUnit.SECONDS);
            Multithreading.schedule(() -> shouldRetry(), (new Random().nextInt(120 - 110) + 110), TimeUnit.SECONDS);
        }
    }

    public void shouldRetry() {
        LocrawInfo locraw = HypixelUtils.INSTANCE.getLocrawInfo();
        if (locraw != null && locraw.getGameType() != LocrawInfo.GameType.SKYBLOCK) {
            retry = true;
            Notifications.INSTANCE.send("AutoSkyblockRejoin", "Failed to join Skyblock. Retrying. This may take up to another 2 minutes. If this fails, please report in discord.gg/rejfv9kFJj.");
        }
        else {
            retry = false;
            isFree = true;
            Notifications.INSTANCE.send("AutoSkyblockRejoin", "Should be connected to Skyblock. Please report this in discord.gg/rejfv9kFJj if it did not work.");
        }
    }
}
