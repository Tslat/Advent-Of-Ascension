package net.tslat.aoa3.client.render.entity.projectile.bullets;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.tme.api.particle.ParticleBuilder;

public class ColouredTexturedProjectileRenderer<T extends ThrowableProjectile> extends TexturedProjectileRenderer<T> {
	private final int colour;

	public ColouredTexturedProjectileRenderer(final EntityRendererProvider.Context manager, final int colour, final ResourceLocation textureResource) {
		super(manager, textureResource);

		this.colour = colour;
	}

	@Override
	public void render(ThrowableProjectile entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render((T)entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(8)
				.colourTint(this.colour)
				.spawnClientParticles(entity.level());
	}
}