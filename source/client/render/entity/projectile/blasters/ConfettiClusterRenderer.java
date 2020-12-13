package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.Vec3d;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.ConfettiClusterEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

public class ConfettiClusterRenderer extends ParticleProjectileRenderer<ConfettiClusterEntity> {
	public ConfettiClusterRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ConfettiClusterEntity entity, float partialTicks) {
		Vec3d motion = entity.getMotion();

		for (int i = 0; i < 3; i++) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX(), motion.getY(), motion.getZ());
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + -0.05, motion.getY(), motion.getZ());
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + 0.05, motion.getY(), motion.getZ());
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + -0.05, motion.getY(), motion.getZ() + -0.05);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + -0.05, motion.getY(), motion.getZ() + 0.05);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + 0.05, motion.getY(), motion.getZ() + 0.05);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX() + 0.05, motion.getY(), motion.getZ() + -0.05);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX(), motion.getY(), motion.getZ() + -0.05);
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), motion.getX(), motion.getY(), motion.getZ() + 0.05);
		}
	}
}
