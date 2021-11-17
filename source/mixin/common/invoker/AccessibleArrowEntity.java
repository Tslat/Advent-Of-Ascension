package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.entity.projectile.ArrowEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ArrowEntity.class)
public interface AccessibleArrowEntity {
	@Invoker(value = "setFixedColor") // func_191507_d
	void setSpecificColour(int colour);
}
