package net.tslat.aoa3.common.particletype;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleType;

public class FloatingItemFragmentParticleType extends ParticleType<ItemParticleData> {
	public FloatingItemFragmentParticleType(boolean alwaysShow) {
		super(alwaysShow, ItemParticleData.DESERIALIZER);
	}

	@Override
	public Codec<ItemParticleData> codec() {
		return ItemParticleData.codec(this);
	}
}
