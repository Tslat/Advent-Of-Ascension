package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.RainbowShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class RainbowShotRenderer extends ParticleProjectileRenderer<RainbowShotEntity> {
	public RainbowShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RainbowShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 1.5f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(ColourUtil.RED)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 1f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(0xDF9900)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.5f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(ColourUtil.GREEN)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.5f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(ColourUtil.CYAN)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 1f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(ColourUtil.BLUE)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 1.5f, 0))
				.spawnNTimes(3)
				.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
				.colourTint(0xC140D7)
				.spawnClientParticles(entity.level());
	}
}