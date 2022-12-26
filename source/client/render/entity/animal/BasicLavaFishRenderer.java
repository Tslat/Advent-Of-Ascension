package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.content.entity.animal.fish.BasicLavaFishEntity;
import software.bernie.geckolib.model.GeoModel;

public class BasicLavaFishRenderer extends AnimatedMobRenderer<BasicLavaFishEntity> {
	public BasicLavaFishRenderer(EntityRendererProvider.Context renderManager, GeoModel<BasicLavaFishEntity> model) {
		super(renderManager, model, 0.23f);
	}

	@Override
	protected void applyRotations(BasicLavaFishEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

		matrixStackIn.mulPose(Axis.YP.rotationDegrees(4.3f * Mth.sin(0.6f * ageInTicks)));

		if (!entityLiving.isInLava()) {
			matrixStackIn.translate(0.2f, 0.1f, 0);
			matrixStackIn.mulPose(Axis.ZP.rotationDegrees(90));
		}
	}

	@Override
	public void render(BasicLavaFishEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}
}
