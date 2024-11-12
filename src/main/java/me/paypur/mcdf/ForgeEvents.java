package me.paypur.mcdf;

import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static me.paypur.mcdf.MCDF.COOLDOWN;

public class ForgeEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void breakSpeed(PlayerEvent.BreakSpeed event) {
        float hardness = event.getState().getDestroySpeed(null, null);

        if (hardness == 0f) {
            return;
        }

        float breakSpeed = event.getNewSpeed();
        int multiplier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getEntity()) ? 30 : 100;
        int level = event.getEntity().getMainHandItem().getEnchantmentLevel(COOLDOWN.get());

        if (hardness * multiplier / (level + 1) < breakSpeed) {
            event.setNewSpeed(hardness * multiplier / (level + 1));
        }
    }

}
