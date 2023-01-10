package dev.microcontrollers.autoskyblockrejoin;

import dev.microcontrollers.autoskyblockrejoin.config.RejoinConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = AutoSkyblockRejoin.MODID, name = AutoSkyblockRejoin.NAME, version = AutoSkyblockRejoin.VERSION, acceptedMinecraftVersions = "1.8.9", clientSideOnly = true)
public class AutoSkyblockRejoin {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    public static RejoinConfig config;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Rejoin());

        config = new RejoinConfig();
    }
}
