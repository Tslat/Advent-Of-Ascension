package net.tslat.aoa3.mixin.client.patch;

import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.storage.PrimaryLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PrimaryLevelData.class)
public class PrimaryLevelDataMixin { // Removes the 'experimental settings' screen from startup
	@Inject(method = "worldGenSettingsLifecycle", at = @At("HEAD"), cancellable = true)
	private void worldGenSettingsLifecycle(CallbackInfoReturnable<Lifecycle> callback) {
		callback.setReturnValue(Lifecycle.stable());
	}
}
