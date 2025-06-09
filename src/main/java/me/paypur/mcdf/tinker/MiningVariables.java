package me.paypur.mcdf.tinker;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;
import slimeknights.tconstruct.library.modifiers.modules.technical.ArmorStatModule;
import slimeknights.tconstruct.library.tools.capability.TinkerDataKeys;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MiningVariables {

    public static class MiningMultiplier implements MiningSpeedVariable {
        public static final MiningMultiplier INSTANCE = new MiningMultiplier();
        public static final RecordLoadable<MiningMultiplier> LOADER = new SingletonLoader<>(INSTANCE);

        @Override
        public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
            if (event == null || player == null) {
                return 30;
            }
            return ForgeHooks.isCorrectToolForDrops(event.getState(), player) ? 30 : 100;
        }

        @Override
        public RecordLoadable<MiningMultiplier> getLoader() {
            return LOADER;
        }
    };

    public static class HasteLevel implements MiningSpeedVariable {
        public static final HasteLevel INSTANCE = new HasteLevel();
        public static final RecordLoadable<HasteLevel> LOADER = new SingletonLoader<>(INSTANCE);

        @Override
        public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
            if (event == null || player == null) {
                return 1;
            }
            float haste = ArmorStatModule.getStat(player, TinkerDataKeys.MINING_SPEED);
            return (haste > 0) ? 1.1f * haste : 1;
        }

        @Override
        public RecordLoadable<HasteLevel> getLoader() {
            return LOADER;
        }
    }

}
