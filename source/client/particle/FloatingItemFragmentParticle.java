package net.tslat.aoa3.client.particle;

import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class FloatingItemFragmentParticle extends BreakingParticle {
	public FloatingItemFragmentParticle(ClientWorld level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, ItemStack stack) {
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

	public static class Factory implements IParticleFactory<ItemParticleData> {
		@Nullable
		@Override
		public Particle createParticle(ItemParticleData data, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new FloatingItemFragmentParticle(world, x, y, z, velocityX, velocityY, velocityZ, data.getItem());
		}
	}
}