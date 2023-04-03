package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public abstract class BiomeMixin {
	@Shadow public abstract Biome.Precipitation getPrecipitationAt(BlockPos pos);

	// Disables snowing for biomes where precipitation is set to none. This prevents dry/alien biomes from gathering snow layers when they shouldn't
	@Inject(method = "shouldSnow", at = @At("HEAD"), cancellable = true)
	private void shouldSnow(LevelReader world, BlockPos pos, CallbackInfoReturnable<Boolean> callback) {
		if (getPrecipitationAt(pos) == Biome.Precipitation.NONE)
			callback.setReturnValue(false);
	}
}
