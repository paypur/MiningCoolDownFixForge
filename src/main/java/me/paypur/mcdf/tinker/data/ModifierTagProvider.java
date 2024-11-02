package me.paypur.mcdf.tinker.data;

import me.paypur.mcdf.MCDF;
import me.paypur.mcdf.tinker.ModifierIds;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

import static slimeknights.tconstruct.common.TinkerTags.Modifiers.*;

public class ModifierTagProvider extends AbstractModifierTagProvider {

    public ModifierTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, MCDF.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(GENERAL_SLOTLESS).add(ModifierIds.COOL_DOWN);
    }

    @Override
    public String getName() {
        return "Mining Cooldown Fix modifier tags";
    }
}
