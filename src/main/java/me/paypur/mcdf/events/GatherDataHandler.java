package me.paypur.mcdf.events;

import me.paypur.mcdf.MCDF;
import me.paypur.mcdf.data.MCDFDatapackProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MCDF.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class GatherDataHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput pack = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        generator.addProvider(event.includeServer(), new MCDFDatapackProvider(pack, lookup));
    }
}
