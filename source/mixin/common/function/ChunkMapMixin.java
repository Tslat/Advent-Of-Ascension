package net.tslat.aoa3.mixin.common.function;

import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.tslat.aoa3.advent.AdventOfAscension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkMap.class)
public class ChunkMapMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getSeed()J"), require = 0)
	public long wrapDimensionSeed(ServerLevel level) {
		if (level.dimension().location().getNamespace().equals(AdventOfAscension.MOD_ID))
			return level.getSeed() + level.dimension().location().hashCode();

		return level.getSeed();
	}
}
