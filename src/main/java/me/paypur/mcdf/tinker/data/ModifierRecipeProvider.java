package me.paypur.mcdf.tinker.data;

import me.paypur.mcdf.MCDF;
import me.paypur.mcdf.tinker.ModifierIds;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.ModifierMatch;
import slimeknights.tconstruct.library.recipe.modifiers.adding.IncrementalModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;

import java.util.function.Consumer;

public class ModifierRecipeProvider extends RecipeProvider implements IRecipeHelper {

    public ModifierRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        String upgradeFolder = "tools/modifiers/upgrade/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";

        ModifierRecipeBuilder.modifier(ModifierIds.COOL_DOWN)
                .setTools(TinkerTags.Items.HARVEST)
                .addInput(Items.REPEATER)
                .setMaxLevel(5)
                .setSlots(SlotType.UPGRADE, 1)
                .saveSalvage(consumer, prefix(ModifierIds.COOL_DOWN, upgradeSalvage))
                .save(consumer, prefix(ModifierIds.COOL_DOWN, upgradeFolder));
    }

    @Override
    public String getModId() {
        return MCDF.MOD_ID;
    }

    @Override
    public String getName() {
        return "Mining Cooldown Fix modifier recipes";
    }

}
