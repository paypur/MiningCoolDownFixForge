package me.paypur.mcdf.tinker.variable;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorStatModule;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MiningVariables {

    public static final MiningSpeedVariable MINING_MULTI = GenericLoaderRegistry.SingletonLoader.singleton(loader ->
        new MiningSpeedVariable() {
            @Override
            public GenericLoaderRegistry.IGenericLoader<? extends MiningSpeedVariable> getLoader() {
                return loader;
            }

            @Override
            public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
                if (event == null || player == null) {
                    return 30;
                }
                return ForgeHooks.isCorrectToolForDrops(event.getState(), player) ? 30 : 100;
            }
        }
    );

    public static final MiningSpeedVariable HASTE_LEVEL = GenericLoaderRegistry.SingletonLoader.singleton(loader ->
        new MiningSpeedVariable() {
            @Override
            public GenericLoaderRegistry.IGenericLoader<? extends MiningSpeedVariable> getLoader() {
                return loader;
            }

            @Override
            public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
                if (event == null || player == null) {
                    return 1;
                }
                float haste = ArmorStatModule.getStat(player, TinkerDataKeys.MINING_SPEED);
                return (haste > 0) ? 1.1f * haste : 1;
            }
        }
    );

}
