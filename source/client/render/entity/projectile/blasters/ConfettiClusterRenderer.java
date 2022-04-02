package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.ConfettiClusterEntity;

public class ConfettiClusterRenderer extends ParticleProjectileRenderer<ConfettiClusterEntity> {
	public ConfettiClusterRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ConfettiClusterEntity entity, float partialTicks) {
		Vec3 motion = entity.getDeltaMovement();

		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x(), motion.y(), motion.z());
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + -0.05, motion.y(), motion.z());
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + 0.05, motion.y(), motion.z());
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + -0.05, motion.y(), motion.z() + -0.05);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + -0.05, motion.y(), motion.z() + 0.05);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + 0.05, motion.y(), motion.z() + 0.05);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x() + 0.05, motion.y(), motion.z() + -0.05);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x(), motion.y(), motion.z() + -0.05);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getX(), entity.getY(), entity.getZ(), motion.x(), motion.y(), motion.z() + 0.05);
		}
	}
}