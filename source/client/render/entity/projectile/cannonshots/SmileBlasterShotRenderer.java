package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.SmileBlasterEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class SmileBlasterShotRenderer extends TexturedProjectileRenderer<SmileBlasterEntity> {
	public SmileBlasterShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(SmileBlasterEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			float colour = entity.particleColourStage-- < 12 ?
						   0 :
						   entity.level().random.nextFloat() * 0.7f + 0.3f;

			if (entity.particleColourStage < 0)
				entity.particleColourStage = 24;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(colour, colour, 0, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}