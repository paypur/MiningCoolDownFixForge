package me.paypur.mcdf;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static me.paypur.mcdf.MCDF.COOLDOWN;

public class ForgeEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void breakSpeed(PlayerEvent.BreakSpeed event) {
        // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360007479999/comments/360001541500
//            GLFW.glfwSetInputMode(Minecraft.getInstance().getWindow().getWindow() , GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        float hardness = event.getState().getDestroySpeed(null, null);
        float breakSpeed = event.getNewSpeed();
        int multiplier = ForgeHooks.isCorrectToolForDrops(event.getState(), event.getPlayer()) ? 30 : 100;
        int level = EnchantmentHelper.getItemEnchantmentLevel(COOLDOWN.get(), event.getPlayer().getMainHandItem());
        if (hardness * multiplier / (level + 1) < breakSpeed) {
            event.setNewSpeed(hardness * multiplier / (level + 1));
        }
    }

    @SubscribeEvent
    void addTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack book = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(COOLDOWN.get(), 1));
            trades.get(1).add(new BasicItemListing(4, book, 1, 1));
        }
    }

}
