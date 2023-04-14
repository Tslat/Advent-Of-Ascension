package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.tslat.aoa3.common.registration.AoATags;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow @Final private Minecraft minecraft;

	@Redirect(method = "bobHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;sin(F)F"), require = 0)
	private float wrapFlinchlessSin(float value) {
		if (this.minecraft.player.getLastDamageSource() != null && this.minecraft.player.getLastDamageSource().is(AoATags.DamageTypes.REDUCED_FLINCH))
			value *= 0.1f;

		return Mth.sin(value);
	}
}
