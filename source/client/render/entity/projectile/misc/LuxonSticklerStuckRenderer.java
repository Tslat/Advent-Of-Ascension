package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.misc.LuxonSticklerStuckEntity;
import net.tslat.aoa3.util.ColourUtil;

public class LuxonSticklerStuckRenderer extends ParticleProjectileRenderer<LuxonSticklerStuckEntity> {
	public LuxonSticklerStuckRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(LuxonSticklerStuckEntity entity, float partialTicks) {
		for (int i = 0; i < 7; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.CYAN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}