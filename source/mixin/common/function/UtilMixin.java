package net.tslat.aoa3.mixin.common.function;

import net.minecraft.Util;
import net.tslat.aoa3.scheduling.AoAScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Util.class)
public abstract class UtilMixin {
    @Inject(method = "shutdownExecutors", at = @At("HEAD"))
    private static void aoa$injectShutdownCallback(CallbackInfo ci) {
        AoAScheduler.shutdown(Util::shutdownExecutor);
    }
}
