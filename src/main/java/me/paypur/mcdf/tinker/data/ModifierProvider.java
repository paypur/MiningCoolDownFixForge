package me.paypur.mcdf.tinker.data;

import me.paypur.mcdf.tinker.ModifierIds;
import me.paypur.mcdf.tinker.variable.MiningVariables;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.math.ModifierFormula;
import slimeknights.tconstruct.library.json.variable.block.BlockVariable;
import slimeknights.tconstruct.library.json.variable.mining.BlockMiningSpeedVariable;
import slimeknights.tconstruct.library.modifiers.modules.mining.ConditionalMiningSpeedModule;


public class ModifierProvider extends AbstractModifierProvider implements IConditionBuilder {

    public ModifierProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
        buildModifier(ModifierIds.COOL_DOWN)
                .priority(-10000)
                .addModule(ConditionalMiningSpeedModule.builder()
                        .customVariable("hardness", new BlockMiningSpeedVariable(BlockVariable.HARDNESS, 1))
                        .customVariable("multi", MiningVariables.MINING_MULTI)
                        .customVariable("haste", MiningVariables.HASTE_LEVEL)
                        .formula()
                        .customVariable("hardness")
                        .customVariable("multi")
                        .multiply()
                        .constant(1)
                        .variable(ModifierFormula.LEVEL)
                        .add()
                        .divide()
                        .variable(ModifierFormula.VALUE)
                        .customVariable("haste")
                        .divide()
                        .min()
                        .build()
                );
    }

    @Override
    public String getName() {
        return "Mining Cooldown Fix modifiers";
    }

}
