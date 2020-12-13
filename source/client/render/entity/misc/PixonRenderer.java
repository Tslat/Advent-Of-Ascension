package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.render.entity.mob.InvisibleEntityRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

public class PixonRenderer extends InvisibleEntityRenderer {
	private final int colour;

	public PixonRenderer(EntityRendererManager renderManager, int colour) {
		super(renderManager);

		this.colour = colour;
	}

	@Override
	public void render(MobEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1.25f, 3, colour), true, entity.getPosX(), entity.getPosY() + 0.65d, entity.getPosZ(), 0, 0, 0);
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		return null;
	}
}