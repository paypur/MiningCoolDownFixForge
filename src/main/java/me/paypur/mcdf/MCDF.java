package me.paypur.mcdf;

import me.paypur.mcdf.tinker.TinkersEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MCDF.MOD_ID)
public class MCDF {

    public static final String MOD_ID = "mcdf";
    public static final String TCON_ID = "tconstruct";
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final RegistryObject<Enchantment> COOL_DOWN = ENCHANTMENTS.register("cooldown", () -> new CooldownEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public MCDF() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENCHANTMENTS.register(eventBus);

        if (ModList.get().isLoaded(TCON_ID)) {
            eventBus.register(new TinkersEvents());
        }
    }

    // TODO: add texture and trade recipe

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeEventHandler {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void breakSpeed(PlayerEvent.BreakSpeed event) {
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360007479999/comments/360001541500
//            GLFW.glfwSetInputMode(Minecraft.getInstance().getWindow().getWindow() , GLFW_CURSOR, GLFW_CURSOR_NORMAL);
            float hardness = event.getState().getDestroySpeed(null, null);
            float breakSpeed = event.getNewSpeed();
            int multiplier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getPlayer()) ? 30 : 100;
            int level = EnchantmentHelper.getItemEnchantmentLevel(COOL_DOWN.get(), event.getPlayer().getMainHandItem());
            if (hardness * multiplier / (level + 1) < breakSpeed) {
                event.setNewSpeed(hardness * multiplier / (level + 1));
            }
        }
    }

}
