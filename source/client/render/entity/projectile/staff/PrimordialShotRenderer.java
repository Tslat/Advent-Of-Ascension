package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.staff.PrimordialShotEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class PrimordialShotRenderer extends ParticleProjectileRenderer<PrimordialShotEntity> {
	public PrimordialShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(PrimordialShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, NumberUtil.RGB(255, 255, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
