package net.tslat.aoa3.common.particletype;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleType;

public class FloatingItemFragmentParticleType extends ParticleType<ItemParticleOption> {
	public FloatingItemFragmentParticleType(boolean alwaysShow) {
		super(alwaysShow, ItemParticleOption.DESERIALIZER);
	}

	@Override
	public Codec<ItemParticleOption> codec() {
		return ItemParticleOption.codec(this);
	}
}
