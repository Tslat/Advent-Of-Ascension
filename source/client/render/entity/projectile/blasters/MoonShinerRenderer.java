package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.MoonShinerEntity;
import net.tslat.aoa3.util.ColourUtil;

public class MoonShinerRenderer extends ParticleProjectileRenderer<MoonShinerEntity> {
	public MoonShinerRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MoonShinerEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.WHITE), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}