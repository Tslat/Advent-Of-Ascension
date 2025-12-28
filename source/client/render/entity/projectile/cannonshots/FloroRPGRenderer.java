package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.FloroRPGEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class FloroRPGRenderer extends TexturedProjectileRenderer<FloroRPGEntity> {
	public FloroRPGRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(FloroRPGEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.3d, 0))
				.spawnNTimes(8)
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());

		for (int i = 0; i < 8; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.3f, 0))
					.colourTint(0, 0, entity.level().random.nextFloat() * 0.7f + 0.3f, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}