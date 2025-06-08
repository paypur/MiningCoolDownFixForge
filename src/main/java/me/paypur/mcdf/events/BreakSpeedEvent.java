package me.paypur.mcdf.events;


import me.paypur.mcdf.MCDFEnchantments;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


public class BreakSpeedEvent {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    void breakSpeed(PlayerEvent.BreakSpeed event) {
        float hardness = event.getState().getDestroySpeed(null, null);

        if (hardness == 0f || event.getPosition().isEmpty()) return;

        float breakSpeed = event.getNewSpeed();
        int multiplier = EventHooks.doPlayerHarvestCheck(event.getEntity(), event.getState(), event.getEntity().level(), event.getPosition().get()) ? 30 : 100;

        assert Minecraft.getInstance().level != null;
        Holder<Enchantment> holder = Minecraft.getInstance().level.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(MCDFEnchantments.COOLDOWN);

        int level = event.getEntity().getMainHandItem().getEnchantmentLevel(holder);

        if (hardness * multiplier / (level + 1) < breakSpeed) {
            event.setNewSpeed(hardness * multiplier / (level + 1));
        }
    }

}
