package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Redirect(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/damagesource/DamageSource;is(Lnet/minecraft/tags/TagKey;)Z", ordinal = 7), require = 0)
	private boolean wrapKnockbackCheck(DamageSource damageSource, TagKey<DamageType> explosionTag) {
		return damageSource.is(explosionTag) || damageSource.is(DamageTypeTags.NO_IMPACT);
	}
}
