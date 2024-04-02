package dev.microcontrollers.autoskyblockrejoin.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.DualOption;
import cc.polyfrost.oneconfig.config.annotations.Info;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

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

    @Switch(
            name = "Rejoin Hypixel on Disconnect",
            description = "Untested, but should work in theory. Automatically attempts a full reconnect to the network and then to SkyBlock.",
            subcategory = "General"
    )
    public static boolean serverConnect = false;

    public RejoinConfig() {
        super(new Mod("Auto Skyblock Rejoin", ModType.HYPIXEL), "autoskyblockrejoin.json");
        initialize();
    }
}

