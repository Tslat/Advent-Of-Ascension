package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.ConfettiClusterEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

public class ConfettiClusterRenderer extends ParticleProjectileRenderer<ConfettiClusterEntity> {
	public ConfettiClusterRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ConfettiClusterEntity entity, float partialTicks) {
		Vector3d motion = entity.getDeltaMovement();

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
