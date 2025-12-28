/*
package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.projectile.TexturedProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.MultiplyingGrenadeEntity;
import net.tslat.aoa3.util.ColourUtil;

public class MultiplyingGrenadeRenderer extends TexturedProjectileRenderer<MultiplyingGrenadeEntity> {
	public MultiplyingGrenadeRenderer(final EntityRendererProvider.Context manager, final ResourceLocation textureResource) {
		super(manager, textureResource);
	}

	@Override
	public void render(MultiplyingGrenadeEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);

		ParticleBuilder.forPositions(AoAParticleTypes.SPARKLER.get(), entity.position())
        .power(Vec3.ZERO)
        .scaleMod(0.75f)
        .lifespan(Mth.ceil(3 / RandomUtil.valueBetween(0.2f, 1)))
        .colourTint(ColourUtil.YELLOW)
        .spawnClientParticles(entity.level());
	}
}
*/
