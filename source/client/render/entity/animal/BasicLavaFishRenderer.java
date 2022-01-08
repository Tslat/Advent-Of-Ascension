package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.object.entity.animal.fish.BasicLavaFishEntity;

public class BasicLavaFishRenderer extends AnimatedMobRenderer<BasicLavaFishEntity> {
	public BasicLavaFishRenderer(EntityRendererManager renderManager, String assetSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(assetSubpath), shadow);
	}

	public BasicLavaFishRenderer(EntityRendererManager renderManager, String modelSubpath, String textureSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(modelSubpath, textureSubpath), shadow);
	}

	public BasicLavaFishRenderer(EntityRendererManager renderManager, String modelSubpath, String textureSubpath, String animationSubpath, float shadow) {
		super(renderManager, new EntityGeoModel<BasicLavaFishEntity>(modelSubpath, textureSubpath, animationSubpath), shadow);
	}

	@Override
	protected void applyRotations(BasicLavaFishEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);

		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(4.3f * MathHelper.sin(0.6f * ageInTicks)));

		if (!entityLiving.isInLava()) {
			matrixStackIn.translate(0.2f, 0.1f, 0);
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90));
		}
	}
}
