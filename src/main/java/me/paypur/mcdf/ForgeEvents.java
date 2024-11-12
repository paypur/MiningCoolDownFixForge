package me.paypur.mcdf;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
        int multiplier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getPlayer()) ? 30 : 100;
        int level = EnchantmentHelper.getItemEnchantmentLevel(COOLDOWN.get(), event.getPlayer().getMainHandItem());

        if (hardness * multiplier / (level + 1) < breakSpeed) {
            event.setNewSpeed(hardness * multiplier / (level + 1));
        }
    }

}
