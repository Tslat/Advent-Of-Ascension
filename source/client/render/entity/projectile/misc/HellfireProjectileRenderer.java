package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.util.ColourUtil;

public class HellfireProjectileRenderer extends ParticleProjectileRenderer<HellfireProjectileEntity> {
	public HellfireProjectileRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(HellfireProjectileEntity entity, float partialTicks) {
		for (int i = 0; i < 5; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RED), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}