package me.paypur.mcdf.tinker;

import me.paypur.mcdf.tinker.data.ModifierProvider;
import me.paypur.mcdf.tinker.data.ModifierRecipeProvider;
import me.paypur.mcdf.tinker.data.ModifierTagProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegisterEvent;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;

import static me.paypur.mcdf.MCDF.MOD_ID;

public class TinkersEvents {

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput pack = generator.getPackOutput();
        boolean server = event.includeServer();
        generator.addProvider(server, new ModifierProvider(pack));
        generator.addProvider(server, new ModifierRecipeProvider(pack));
        generator.addProvider(server, new ModifierTagProvider(pack, event.getExistingFileHelper()));
    }

    @SubscribeEvent
    void registerSerializers(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "mining_multiplier"), MiningVariables.MiningMultiplier.LOADER);
            MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "haste_level"), MiningVariables.HasteLevel.LOADER);
        }
    }

}
