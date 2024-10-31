package me.paypur.mcdf.mixin;


import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;

@Mixin(MultiPlayerGameMode.class)
public abstract class DebugMixin {

    // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360007479999/comments/360001541500
    @Inject(method = "Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;startDestroyBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Z", at = @At("HEAD"))
    public void debuggerReleaseControl(BlockPos pLoc, Direction pFace, CallbackInfoReturnable<Boolean> cir) {
        GLFW.glfwSetInputMode(Minecraft.getInstance().getWindow().getWindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

}
