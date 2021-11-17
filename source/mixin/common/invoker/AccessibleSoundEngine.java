package net.tslat.aoa3.mixin.common.invoker;

import net.minecraft.client.audio.SoundEngine;
import net.minecraft.util.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import javax.annotation.Nullable;

@Mixin(SoundEngine.class)
public interface AccessibleSoundEngine {
	@Invoker(value = "getVolume") // func_188769_a
	float getCategoryVolume(@Nullable SoundCategory category);
}
