package net.tslat.aoa3.entity.properties;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.advent.AdventOfAscension;

import javax.annotation.Nullable;

public interface BossEntity {
	public ResourceLocation getBossBarTexture();

	@Nullable
	public SoundEvent getBossMusic();

	default void playMusic(Entity entity) {
		if (getBossMusic() != null)
			AdventOfAscension.proxy.playMusic(getBossMusic(), entity);
	}
}
