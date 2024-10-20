package me.paypur.mcdf;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MCDF.MOD_ID)
public class MCDF {

    public static final String MOD_ID = "mcdf";
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final RegistryObject<Enchantment> COOL_DOWN = ENCHANTMENTS.register("cooldown", () -> new CoolDownEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    public MCDF() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ENCHANTMENTS.register(eventBus);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeEventHandler {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void breakSpeed(PlayerEvent.BreakSpeed event) {
            // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360007479999/comments/360001541500
//            GLFW.glfwSetInputMode(Minecraft.getInstance().getWindow().getWindow() , GLFW_CURSOR, GLFW_CURSOR_NORMAL);
            float destroySpeed =  event.getState().getDestroySpeed(null, null);
            float breakSpeed = event.getOriginalSpeed();
            int modifier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getPlayer()) ? 30 : 100;
            int level = EnchantmentHelper.getEnchantmentLevel(COOL_DOWN.get(), event.getPlayer());
            if (destroySpeed * modifier / breakSpeed < level + 1) {
                event.setNewSpeed(destroySpeed * modifier / (level + 1));
            }
        }
    }

}
