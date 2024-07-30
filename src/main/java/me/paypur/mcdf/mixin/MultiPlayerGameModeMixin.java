package me.paypur.mcdf.mixin;

import me.paypur.mcdf.config.MCDFCommonConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;

@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {

    @Shadow @Final private Minecraft minecraft;
    @Shadow GameType localPlayerMode;
    @Shadow @Final private static Logger LOGGER;

    @ModifyConstant(method = "continueDestroyBlock", constant = @Constant(intValue = 5))
    private int MiningCooldownFix(int value) {
        if (localPlayerMode.isSurvival()) {
            ItemStack item = minecraft.player.getItemInHand(InteractionHand.MAIN_HAND);

            // TODO: does not prevent insta mines

            for (Tag tag : item.getEnchantmentTags()) {
//                GLFW.glfwSetInputMode(Minecraft.getInstance().getWindow().getWindow() , GLFW_CURSOR, GLFW_CURSOR_NORMAL);
                CompoundTag e = (CompoundTag) tag;
                if (e.get("id").getAsString().equals("mcdf:thing")) {
                    return ((ShortTag) e.get("lvl")).getAsInt();
                }
            };
            return MCDFCommonConfigs.MINING_DELAY.get();
        }
        return 5;
    }
}
