package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface AccessibleLivingEntity {
	@Invoker(value = "getJumpPower")
	float getJumpVelocity();

	@Invoker(value = "jumpFromGround")
	void jump();
}
