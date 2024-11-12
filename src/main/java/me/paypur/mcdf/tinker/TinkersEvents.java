package me.paypur.mcdf.tinker;

import me.paypur.mcdf.tinker.data.ModifierProvider;
import me.paypur.mcdf.tinker.data.ModifierRecipeProvider;
import me.paypur.mcdf.tinker.data.ModifierTagProvider;
import me.paypur.mcdf.tinker.variable.MiningVariables;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;

import static me.paypur.mcdf.MCDF.MOD_ID;

public class TinkersEvents {

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        boolean server = event.includeServer();
        generator.addProvider(server, new ModifierProvider(generator));
        generator.addProvider(server, new ModifierRecipeProvider(generator));
        generator.addProvider(server, new ModifierTagProvider(generator, event.getExistingFileHelper()));
    }

    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registry.RECIPE_SERIALIZER_REGISTRY) {
            MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "mining_multi"), (GenericLoaderRegistry.IGenericLoader<? extends MiningSpeedVariable>) MiningVariables.MINING_MULTI.getLoader());
            MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "haste_level"), (GenericLoaderRegistry.IGenericLoader<? extends MiningSpeedVariable>) MiningVariables.HASTE_LEVEL.getLoader());
        }
    }

}
