package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
	@Shadow
	public Biome.RainType getPrecipitation() {
		throw new IllegalStateException("Mixin failed to find getPrecipitation() for shadowing.");
	}

	@Inject(method = "shouldSnow", at = @At("HEAD"), cancellable = true)
	private void shouldSnow(IWorldReader world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (getPrecipitation() == Biome.RainType.NONE)
			callback.setReturnValue(false);
	}
}
