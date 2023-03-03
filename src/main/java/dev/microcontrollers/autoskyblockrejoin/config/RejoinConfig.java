package dev.microcontrollers.autoskyblockrejoin.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Info;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class RejoinConfig extends Config {
    @Info(
            text = "Only turn this on when AFK. Turn off when done with your AFK session.",
            size = 2,
            type = InfoType.WARNING,
            subcategory = "General"
    )
    private boolean warning = false;

    @Switch(
            name = "Automatically Rejoin Skyblock",
            subcategory = "General"
    )
    public static boolean autoSkyblockRejoin = false;

    @Switch(
            name = "Warp Garden",
            description = "Word to garden instead of your island.",
            subcategory = "General"
    )
    public static boolean shouldWarpGarden = false;


    @Switch(
            name = "Hold shift on Rejoin",
            description = "Hold shift when rejoining to prevent flying.",
            subcategory = "General"
    )
    public static boolean shouldShift = false;

    @Slider(
            name = "Shift Time",
            description = "Measured in seconds.",
            subcategory = "General",
            min = 1,
            max = 5,
            step = 1
    )
    public static int shiftTime = 1;

    public RejoinConfig() {
        super(new Mod("Auto Skyblock Rejoin", ModType.HYPIXEL), "autoskyblockrejoin.json");
        initialize();
    }
}

