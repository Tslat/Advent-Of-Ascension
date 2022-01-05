package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.mob.CherryShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class CherryShotRenderer extends ParticleProjectileRenderer<CherryShotEntity> {
	public CherryShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(CherryShotEntity entity, float partialTicks) {
		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.RED), entity.getX(), entity.getY() + 0.2f, entity.getZ(), 0, 0, 0);
		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, ColourUtil.GREEN), entity.getX(), entity.getY() - 0.2f, entity.getZ(), 0, 0, 0);
	}
}