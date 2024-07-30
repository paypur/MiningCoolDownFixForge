package me.paypur.mcdf;

import me.paypur.mcdf.config.MCDFCommonConfigs;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MCDF.MOD_ID)
public class MCDF {

    public static final String MOD_ID = "mcdf";
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final RegistryObject<Enchantment> thing = ENCHANTMENTS.register("thing", () -> new ThingEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public MCDF() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENCHANTMENTS.register(eventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MCDFCommonConfigs.SPEC, "miningcooldownfixforge-common.toml");
    }

}
