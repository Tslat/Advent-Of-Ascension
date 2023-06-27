/*
package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.mob.InvisibleEntityRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;

public class PixonRenderer extends InvisibleEntityRenderer {
	private final int colour;

	public PixonRenderer(EntityRendererProvider.Context renderManager, int colour) {
		super(renderManager);

		this.colour = colour;
	}

	@Override
	public void render(Mob entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
		entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1.25f, 3, colour), true, entity.getX(), entity.getY() + 0.65d, entity.getZ(), 0, 0, 0);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return null;
	}
}*/
