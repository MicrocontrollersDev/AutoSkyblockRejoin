package dev.microcontrollers.autoskyblockrejoin.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Info;
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
            name = "Hytils Reborn Warning",
            description = "Hytils Reborn is required for this mod to work. Specifically the limbo /play helper feature.",
            subcategory = "General"
    )
    public static boolean hytilsWarning = true;

    public RejoinConfig() {
        super(new Mod("Auto Skyblock Rejoin", ModType.HYPIXEL), "autoskyblockrejoin.json");
        initialize();
    }
}

