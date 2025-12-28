package net.tslat.aoa3.mixin.client.function;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.tslat.aoa3.client.render.dimension.AoADimensionEffectsRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;animateTick(III)V"))
    public void aoa3$wrapFXTick(ClientLevel level, int playerX, int playerY, int playerZ, Operation<Void> callback) {
        if (level.effects() instanceof AoADimensionEffectsRenderer aoaEffects) {
            aoaEffects.doFXTick(level, new BlockPos(playerX, playerY, playerZ));
        }
        else {
            callback.call(level, playerX, playerY, playerZ);
        }
    }
}
