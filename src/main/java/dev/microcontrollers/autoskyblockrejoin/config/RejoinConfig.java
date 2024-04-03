package dev.microcontrollers.autoskyblockrejoin.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.annotations.Button;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RejoinConfig extends Config {
    @Info(
            text = "Only turn this on when AFK. Turn off when done with your AFK session. The mod can be toggled in the main OneConfig screen.",
            size = 2,
            type = InfoType.WARNING,
            subcategory = "General"
    )
    private boolean warning = false;

    @Switch(
            name = "Rejoin Skyblock",
            description = "This is a general mod toggle.",
            subcategory = "General"
    )
    public static boolean autoSkyblockRejoin = false;

    @DualOption(
            name = "Warp Destination",
            description = "Word to garden instead of your island.",
            subcategory = "General",
            left = "Island",
            right = "Garden"
    )
    public static boolean warpDestinationGarden = false;


    @Switch(
            name = "Hold Sneak on Rejoin",
            description = "Hold sneak when rejoining to prevent flying.",
            subcategory = "General"
    )
    public static boolean shouldShift = false;

    @Slider(
            name = "Sneak Time",
            description = "Measured in seconds.",
            subcategory = "General",
            min = 1,
            max = 5,
            step = 1
    )
    public static int shiftTime = 1;

    @Info(
            text = "This feature has not been tested! Use at your own risk! Please report in the discord if it works correctly.",
            size = 2,
            type = InfoType.ERROR,
            subcategory = "General"
    )
    private boolean error = false;

    @Switch(
            name = "Rejoin Hypixel on Disconnect",
            description = "Automatically attempts a full reconnect to the network and then to SkyBlock.",
            subcategory = "General"
    )
    public static boolean serverConnect = false;

    @Button(
            name = "Join the Discord",
            text = "Click",
            subcategory = "Socials"
    )
    Runnable discord = () -> {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/rejfv9kFJj"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @Button(
            name = "Check Out My Other Mods",
            text = "Click",
            subcategory = "Socials"
    )
    Runnable modrinth = () -> {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("https://modrinth.com/user/Microcontrollers"));
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public RejoinConfig() {
        super(new Mod("Auto Skyblock Rejoin", ModType.HYPIXEL), "autoskyblockrejoin.json");
        initialize();
    }
}

