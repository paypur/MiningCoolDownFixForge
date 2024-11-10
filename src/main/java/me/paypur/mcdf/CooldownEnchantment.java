package me.paypur.mcdf;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.DiggingEnchantment;

public class CooldownEnchantment extends DiggingEnchantment {

    protected CooldownEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pApplicableSlots);
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    // includes swords compared to super
    @Override
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof ShearsItem || pStack.getItem() instanceof TieredItem;
    }
}
