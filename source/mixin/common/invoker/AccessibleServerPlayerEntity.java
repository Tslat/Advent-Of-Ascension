package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerPlayerEntity.class)
public interface AccessibleServerPlayerEntity {
	@Invoker(value = "triggerDimensionChangeTriggers") // func_213846_b
	void triggerDimensionChangeListeners(ServerWorld world);
}
