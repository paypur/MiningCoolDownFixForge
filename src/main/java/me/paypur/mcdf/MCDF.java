package me.paypur.mcdf;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MCDF.MOD_ID)
public class MCDF {

    public static final String MOD_ID = "mcdf";
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final RegistryObject<Enchantment> COOLDOWN = ENCHANTMENTS.register("cooldown", () -> new CooldownEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));

    public MCDF() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        ENCHANTMENTS.register(modEventBus);

        forgeEventBus.register(new ForgeEvents());
    }

}
