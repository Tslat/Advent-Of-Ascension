package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class VoxCannonShotRenderer extends TexturedProjectileRenderer<PhysicalWeaponProjectile> {
	public VoxCannonShotRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(PhysicalWeaponProjectile entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		for (int i = 0; i < 8; i++) {
			
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(0, entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 1f)
					.spawnClientParticles(entity.level());
		}

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(8)
				.colourTint(ColourUtil.BLACK)
				.spawnClientParticles(entity.level());
	}
}