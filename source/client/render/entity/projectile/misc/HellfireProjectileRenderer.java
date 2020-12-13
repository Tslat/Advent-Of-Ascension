package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class HellfireProjectileRenderer extends ParticleProjectileRenderer<HellfireProjectileEntity> {
	public HellfireProjectileRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(HellfireProjectileEntity entity, float partialTicks) {
		for (int i = 0; i < 5; i++) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, NumberUtil.RGB(255, 0, 0)), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0, 0, 0);
		}
	}
}
