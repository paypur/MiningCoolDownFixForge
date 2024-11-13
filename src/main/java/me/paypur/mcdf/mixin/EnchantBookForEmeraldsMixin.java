package me.paypur.mcdf.mixin;

import me.paypur.mcdf.MCDF;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.npc.VillagerTrades;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerTrades.EnchantBookForEmeralds.class)
public class EnchantBookForEmeraldsMixin {

    @Redirect(
            method = "getOffer(Lnet/minecraft/world/entity/Entity;Ljava/util/Random;)Lnet/minecraft/world/item/trading/MerchantOffer;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMinLevel()I"
            )
    )
    public int getMinLevel(Enchantment pEnchantment) {
        return pEnchantment.equals(MCDF.COOLDOWN.get()) ? 0 : pEnchantment.getMinLevel();
    }

    @Redirect(
            method = "getOffer(Lnet/minecraft/world/entity/Entity;Ljava/util/Random;)Lnet/minecraft/world/item/trading/MerchantOffer;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;getMaxLevel()I"
            )
    )
    public int getMaxLevel(Enchantment pEnchantment) {
        return pEnchantment.equals(MCDF.COOLDOWN.get()) ? 0 : pEnchantment.getMaxLevel();
    }

    @ModifyArg(
            method = "getOffer(Lnet/minecraft/world/entity/Entity;Ljava/util/Random;)Lnet/minecraft/world/item/trading/MerchantOffer;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/EnchantmentInstance;<init>(Lnet/minecraft/world/item/enchantment/Enchantment;I)V"
            ),
            index = 1
    )
    private int EnchantmentInstance(Enchantment pEnchantment, int pLevel){
        return pEnchantment.equals(MCDF.COOLDOWN.get()) ? 1 : pLevel;
    }

    @Redirect(method = "getOffer(Lnet/minecraft/world/entity/Entity;Ljava/util/Random;)Lnet/minecraft/world/item/trading/MerchantOffer;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/enchantment/Enchantment;isTreasureOnly()Z"
            )
    )
    private boolean isTreasureOnly(Enchantment instance) {
        return !instance.equals(MCDF.COOLDOWN.get()) && instance.isTreasureOnly();
    }

}
