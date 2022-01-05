package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.blaster.SwarmShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class SwarmShotRenderer extends ParticleProjectileRenderer<SwarmShotEntity> {
	public SwarmShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SwarmShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.RGB(146, 98, 57)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}