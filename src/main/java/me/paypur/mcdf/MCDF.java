package me.paypur.mcdf;

import com.mojang.logging.LogUtils;
import me.paypur.mcdf.config.MCDFCommonConfigs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MCDF.MOD_ID)
public class MCDF {
    public static final String MOD_ID = "mcdf";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MCDF()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MCDFCommonConfigs.SPEC, "miningcooldownfixforge-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
    }

}
