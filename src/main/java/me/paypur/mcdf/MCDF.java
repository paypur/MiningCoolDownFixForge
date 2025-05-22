package me.paypur.mcdf;

import me.paypur.mcdf.events.BreakSpeedEvent;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MCDF.MOD_ID)
public class MCDF {

    public static final String MOD_ID = "mcdf";
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, MOD_ID);

    public MCDF(IEventBus bus) {
        ENCHANTMENTS.register(bus);

        NeoForge.EVENT_BUS.register(new BreakSpeedEvent());
    }

}
