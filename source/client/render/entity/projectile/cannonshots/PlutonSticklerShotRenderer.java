package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.PlutonSticklerShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class PlutonSticklerShotRenderer extends ParticleProjectileRenderer<PlutonSticklerShotEntity> {
	private final ResourceLocation texture;

	public PlutonSticklerShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	protected void addParticles(PlutonSticklerShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(14)
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());
	}
}