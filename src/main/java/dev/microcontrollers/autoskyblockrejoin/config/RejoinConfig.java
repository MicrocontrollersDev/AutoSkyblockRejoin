package dev.microcontrollers.autoskyblockrejoin.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class RejoinConfig extends Config {
    @Switch(
            name = "Automatically Rejoin Skyblock",
            subcategory = "General"
    )
    public static boolean autoSkyblockRejoin = false;

    public RejoinConfig() {
        super(new Mod("Auto Skyblock Rejoin", ModType.HYPIXEL), "autoskyblockrejoin.json");
        initialize();
    }
}

