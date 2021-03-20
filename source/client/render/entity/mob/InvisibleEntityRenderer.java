package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.misc.InvisibleEntityModel;

public class InvisibleEntityRenderer extends MobRenderer<MobEntity, EntityModel<MobEntity>> {
	public InvisibleEntityRenderer(EntityRendererManager renderManager) {
		super(renderManager, new InvisibleEntityModel(), 0f);
	}

	@Override
	public void render(MobEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {}

	@Override
	public ResourceLocation getTextureLocation(MobEntity entity) {
		return null;
	}
}