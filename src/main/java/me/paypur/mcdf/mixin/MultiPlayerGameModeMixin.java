package me.paypur.mcdf.mixin;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @Shadow GameType localPlayerMode;

    @ModifyConstant(method = "continueDestroyBlock", constant = @Constant(intValue = 5))
    private int MiningCooldownFix(int value) {
        return localPlayerMode.isSurvival() ? 0 : 5;
    }
}
