package net.tslat.aoa3.client.render.entity.projectile.staff;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;

public class LunarFallRenderer extends TexturedProjectileRenderer<NonPhysicalWeaponProjectile> {
	public LunarFallRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(NonPhysicalWeaponProjectile entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 3; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.25f, 0))
				.colourTint(colourMod, colourMod, colourMod, 1f)
				.spawnClientParticles(entity.level());
		}
	}
}