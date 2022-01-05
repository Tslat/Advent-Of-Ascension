package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.staff.ShyreShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class ShyreShotRenderer extends ParticleProjectileRenderer<ShyreShotEntity> {
	public ShyreShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ShyreShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.CYAN), entity.getX(), entity.getY() + 0.25d, entity.getZ(), 0, 0, 0);
		}
	}
}