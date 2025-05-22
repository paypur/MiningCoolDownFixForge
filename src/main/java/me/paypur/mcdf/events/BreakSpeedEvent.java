package me.paypur.mcdf.events;


import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static me.paypur.mcdf.MCDFEnchantments.COOLDOWN;

public class BreakSpeedEvent {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void breakSpeed(PlayerEvent.BreakSpeed event) {
        float hardness = event.getState().getDestroySpeed(null, null);

        if (hardness == 0f) {
            return;
        }

        float breakSpeed = event.getNewSpeed();
        int multiplier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getEntity()) ? 30 : 100;
        int level = event.getEntity().getMainHandItem().getEnchantmentLevel(COOLDOWN);

        if (hardness * multiplier / (level + 1) < breakSpeed) {
            event.setNewSpeed(hardness * multiplier / (level + 1));
        }
    }

}
