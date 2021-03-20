package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.particles.ParticleTypes;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.staff.SunShotEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

public class SunShotRenderer extends ParticleProjectileRenderer<SunShotEntity> {
	public SunShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SunShotEntity entity, float partialTicks) {
		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 2, 3, NumberUtil.RGB(255, 255, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 2, 3, NumberUtil.RGB(255, 0, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);

		double posX = entity.getX() + RandomUtil.randomGaussianValue() * 0.5;
		double posY = entity.getY() + RandomUtil.randomGaussianValue() * 0.5;
		double posZ = entity.getZ() + RandomUtil.randomGaussianValue() * 0.5;

		if (!Minecraft.getInstance().isPaused())
			entity.level.addParticle(ParticleTypes.FLAME, posX, posY, posZ, 0, 0, 0);
	}
}
