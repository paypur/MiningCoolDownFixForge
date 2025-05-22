package me.paypur.mcdf;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class MCDFEnchantments {

    public static final ResourceKey<Enchantment> COOLDOWN = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MCDF.MOD_ID, "cooldown"));

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderGetter<Item> items = context.lookup(Registries.ITEM);

        register(
                context,
                COOLDOWN,
                Enchantment.enchantment(
                        Enchantment.definition(
                                items.getOrThrow(ItemTags.MINING_ENCHANTABLE),
                                10,
                                5,
                                Enchantment.dynamicCost(1, 11),
                                Enchantment.dynamicCost(12, 11),
                                1,
                                EquipmentSlotGroup.MAINHAND
                        )
                )
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

}
