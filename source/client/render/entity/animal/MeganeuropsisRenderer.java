package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.animal.MeganeuropsisEntity;

public class MeganeuropsisRenderer extends MobRenderer<MeganeuropsisEntity, EntityModel<MeganeuropsisEntity>> {
	private static final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/passive/meganeuropsis.png");
	public MeganeuropsisRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, null/*new MeganeuropsisModel()*/, AoAAnimals.MEGANEUROPSIS.get().getWidth() / 3f);
	}

	@Override
	protected void scale(MeganeuropsisEntity entity, PoseStack matrix, float partialTicks) {
		if (entity.isLanded() && entity.isPassenger())
			matrix.translate(0, -entity.getBbHeight() * 0.75f, 0);

		if (entity.getStartLandingTicks() > 0 && entity.getLandingDirection() != Direction.UP) {
			float landPercentage = (float)Mth.clamp(entity.tickCount - entity.getStartLandingTicks(), 0, 10) / 10;
			float rotAmount = 90 * landPercentage;

			switch (entity.getLandingDirection()) {
				case NORTH -> {
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage, 0);
				}
				case SOUTH -> {
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					//matrix.rotate(new Quaternion(rotAmount * 2, 0, 1, 0);
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage, 0);
				}
				case EAST -> {
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					//matrix.rotate(rotAmount, 0, 0, 1);
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage, 0);
				}
				case WEST -> {
					matrix.mulPose(Axis.XP.rotationDegrees(-rotAmount));
					matrix.mulPose(Axis.ZP.rotationDegrees(-rotAmount));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage, 0);
				}
				default -> {
				}
			}
		}
	}

	@Override
	public ResourceLocation getTextureLocation(MeganeuropsisEntity entity) {
		return texture;
	}
}
