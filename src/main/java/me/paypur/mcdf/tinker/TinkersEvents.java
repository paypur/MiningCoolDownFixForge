package me.paypur.mcdf.tinker;

import me.paypur.mcdf.tinker.data.ModifierProvider;
import me.paypur.mcdf.tinker.data.ModifierRecipeProvider;
import me.paypur.mcdf.tinker.data.ModifierTagProvider;
import me.paypur.mcdf.tinker.variable.MiningVariables;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;

import static me.paypur.mcdf.MCDF.MOD_ID;

public class TinkersEvents {

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new ModifierProvider(generator));
            generator.addProvider(new ModifierRecipeProvider(generator));
            generator.addProvider(new ModifierTagProvider(generator, event.getExistingFileHelper()));
        }
    }

    @SubscribeEvent
    void registerSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
        MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "mining_multi"), MiningVariables.MINING_MULTI.getLoader());
        MiningSpeedVariable.LOADER.register(new ResourceLocation(MOD_ID, "haste_level"), MiningVariables.HASTE_LEVEL.getLoader());
    }

}
