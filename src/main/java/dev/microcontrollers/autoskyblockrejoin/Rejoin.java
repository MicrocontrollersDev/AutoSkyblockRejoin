package dev.microcontrollers.autoskyblockrejoin;

import cc.polyfrost.oneconfig.libs.universal.UDesktop;
import cc.polyfrost.oneconfig.utils.Multithreading;
import cc.polyfrost.oneconfig.utils.Notifications;
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil;
import dev.microcontrollers.autoskyblockrejoin.config.RejoinConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.net.URI;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Rejoin {
    private static final String[] disconnectMessages = {
            "An exception occurred in your connection, so you were put in the SkyBlock Lobby!",
            "Evacuating to Hub...",
            "[Important] This server will restart soon: Game Update",
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
    public boolean attempting = false;
    public boolean reconnect = false;

    @SubscribeEvent
    public void onChat(final ClientChatReceivedEvent event) {
        if (!RejoinConfig.autoSkyblockRejoin) return;
        if (!HypixelUtils.INSTANCE.isHypixel()) return;
        if (!AutoSkyblockRejoin.config.enabled) return;
        String message = event.message.getUnformattedText();
        for (String disc : disconnectMessages) {
            if (message.equals(disc)) {
                if (isFree) warpAttempt = true;
                break;
            }
        }
        rejoin();
    }

    public void rejoin() {
        if ((warpAttempt || retry) && RejoinConfig.autoSkyblockRejoin && HypixelUtils.INSTANCE.isHypixel()) {
            warpAttempt = false;
            isFree = false;
            if (!retry && !reconnect) Notifications.INSTANCE.send("AutoSkyblockRejoin", "Forced disconnect detected. Attempting to rejoin Skyblock. This will take around 2 minutes.");
            if (reconnect) Notifications.INSTANCE.send("AutoSkyblockRejoin", "Successfully rejoined Hypixel. Attempting to rejoin Skyblock. This will take around 2 minutes.");
            retry = false;
            reconnect = false;
            Multithreading.schedule(() -> Notifications.INSTANCE.send("AutoSkyblockRejoin", "Attempting to join the lobby. This may take several seconds."), 20, TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/l"), (new Random().nextInt(35 - 25) + 25), TimeUnit.SECONDS);
            Multithreading.schedule(() -> Notifications.INSTANCE.send("AutoSkyblockRejoin", "Attempting to join skyblock. This may take several seconds."), 45, TimeUnit.SECONDS);
            Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/play skyblock"), (new Random().nextInt(70 - 50) + 50), TimeUnit.SECONDS);
            if (!RejoinConfig.shouldWarpGarden) {
                Multithreading.schedule(() -> Notifications.INSTANCE.send("AutoSkyblockRejoin", "Attempting to join your island. This may take several seconds."), 95, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/is"), (new Random().nextInt(110 - 100) + 100), TimeUnit.SECONDS);
            } else {
                Multithreading.schedule(() -> Notifications.INSTANCE.send("AutoSkyblockRejoin", "Attempting to join the garden. This may take several seconds."), 95, TimeUnit.SECONDS);
                Multithreading.schedule(() -> Minecraft.getMinecraft().thePlayer.sendChatMessage("/warp garden"), (new Random().nextInt(110 - 100) + 100), TimeUnit.SECONDS);
            }
            Multithreading.schedule(this::shouldRetry, (new Random().nextInt(120 - 110) + 110), TimeUnit.SECONDS);
        }
    }

    public void shouldRetry() {
        LocrawInfo locraw = LocrawUtil.INSTANCE.getLocrawInfo();
        if (locraw != null && locraw.getGameType() != LocrawInfo.GameType.SKYBLOCK) {
            retry = true;
            Notifications.INSTANCE.send("AutoSkyblockRejoin", "Failed to join Skyblock. Retrying. This may take up to another 2 minutes. If this fails, please report in the discord.",  10000f, () -> UDesktop.browse(URI.create("https://discord.gg/rejfv9kFJj")));
        }
        else {
            retry = false;
            isFree = true;
            Notifications.INSTANCE.send("AutoSkyblockRejoin", "Should be connected to Skyblock. Please report this in the discord if it did not work.", 10000f, () -> UDesktop.browse(URI.create("https://discord.gg/rejfv9kFJj")));
            if (RejoinConfig.shouldShift) {
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode(), true);
                Multithreading.schedule(KeyBinding::unPressAllKeys, RejoinConfig.shiftTime, TimeUnit.SECONDS);
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!AutoSkyblockRejoin.config.enabled) return;
        if (Minecraft.getMinecraft().currentScreen instanceof GuiDisconnected && RejoinConfig.serverConnect && !attempting) {
            Notifications.INSTANCE.send("AutoSkyblockRejoin", "Server disconnect detected. Attempting to rejoin Hypixel. Please report this in the discord if it did not work.", () -> UDesktop.browse(URI.create("https://discord.gg/rejfv9kFJj")));
            attempting = true;
            reconnect = true;
            Multithreading.schedule(() -> FMLClientHandler.instance().connectToServer(
                    new GuiMultiplayer(Minecraft.getMinecraft().currentScreen),
                    new ServerData("hypixel", "hypixel.net", false)), (new Random().nextInt(3) + 3), TimeUnit.SECONDS); // between 3 and 5 seconds
            Multithreading.schedule(this::shouldRetry, (new Random().nextInt(3) + 5), TimeUnit.SECONDS);
            Multithreading.schedule(() -> attempting = false, 10, TimeUnit.SECONDS);
        }
    }
}
