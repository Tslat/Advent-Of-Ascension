package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.misc.BloodlustModel;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.misc.BloodlustEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class BloodlustRenderer extends LivingRenderer<BloodlustEntity, EntityModel<BloodlustEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/misc/bloodlust.png");

	public BloodlustRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BloodlustModel(), 0);
	}

	@Override
	public void render(BloodlustEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(255, 0, 0)), entity.getX(), entity.getY() + 0.5f, entity.getZ(), 0, 0, 0);
	}

	@Override
	protected void scale(BloodlustEntity entity, MatrixStack matrix, float partialTicks) {
		matrix.scale(0.5f, 0.5f, 0.5f);
	}

	@Override
	protected boolean shouldShowName(BloodlustEntity entity) {
		return entity.shouldShowName() && entity.hasCustomName();
	}

	@Override
	public ResourceLocation getTextureLocation(BloodlustEntity entity) {
		return texture;
	}
}
