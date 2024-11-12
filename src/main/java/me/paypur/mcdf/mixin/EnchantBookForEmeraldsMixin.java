package me.paypur.mcdf.mixin;

import me.paypur.mcdf.MCDF;
import net.minecraft.core.Registry;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.entity.npc.VillagerTrades;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class EnchantBookForEmeraldsMixin {

    @Final
    @Shadow
    private int villagerXp;

    /**
     * @author paypur
     * @reason makes cooldown much cheaper
     */
    @Overwrite
    public MerchantOffer getOffer(Entity pTrader, Random pRand) {
        List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
        Enchantment enchantment = list.get(pRand.nextInt(list.size()));
        boolean f = !enchantment.equals(MCDF.COOLDOWN.get());
        int i = f ? Mth.nextInt(pRand, enchantment.getMinLevel(), enchantment.getMaxLevel()) : 1;
        ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
        int j = f ? 2 + pRand.nextInt(5 + i * 10) + 3 * i : 1;
        if (enchantment.isTreasureOnly()) {
            j *= 2;
        }

        if (j > 64) {
            j = 64;
        }

        return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
    }

}
