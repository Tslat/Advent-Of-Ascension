package net.tslat.aoa3.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class FloatingItemFragmentParticle extends BreakingItemParticle {
	public FloatingItemFragmentParticle(ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, ItemStack stack) {
		super(level, x, y, z, stack);

		this.xd *= 0.1f;
		this.yd *= 0.1f;
		this.zd *= 0.1f;
		this.xd += velocityX;
		this.yd += velocityY;
		this.zd += velocityZ;
		this.lifetime *= 2;
	}

	@Override
	public void tick() {
		super.tick();

		if (level.isWaterAt(new BlockPos(x, y, z))) {
			xd *= 0.1f;
			yd *= 0.01f;
			zd *= 0.1f;
		}
	}

	public static class Factory implements ParticleProvider<ItemParticleOption> {
		@Nullable
		@Override
		public Particle createParticle(ItemParticleOption data, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new FloatingItemFragmentParticle(world, x, y, z, velocityX, velocityY, velocityZ, data.getItem());
		}
	}
}