package me.paypur.mcdf.tinker.variable;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.GenericLoaderRegistry;
import slimeknights.tconstruct.library.json.variable.mining.MiningSpeedVariable;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class MiningMulti {

    public static final MiningSpeedVariable DEFAULT = GenericLoaderRegistry.SingletonLoader.singleton(loader -> new MiningSpeedVariable() {
            @Override
            public GenericLoaderRegistry.IGenericLoader<? extends MiningSpeedVariable> getLoader() {
                return loader;
            }

            @Override
            public float getValue(IToolStackView tool, @Nullable PlayerEvent.BreakSpeed event, @Nullable Player player, @Nullable Direction sideHit) {
                if (event == null) {
                    return 30;
                }
                return ForgeHooks.isCorrectToolForDrops(event.getState(), event.getPlayer()) ? 30 : 100;
            }
        }
    );


}
