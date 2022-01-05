package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.client.model.entity.animal.MeganeuropsisModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.animal.MeganeuropsisEntity;

public class MeganeuropsisRenderer extends MobRenderer<MeganeuropsisEntity, EntityModel<MeganeuropsisEntity>> {
	private static final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/passive/meganeuropsis.png");
	public MeganeuropsisRenderer(EntityRendererManager renderManager) {
		super(renderManager, new MeganeuropsisModel(), AoAEntities.Animals.MEGANEUROPSIS.get().getWidth() / 3f);
	}

	@Override
	protected void scale(MeganeuropsisEntity entity, MatrixStack matrix, float partialTicks) {
		if (entity.isLanded() && entity.isPassenger())
			matrix.translate(0, -entity.getBbHeight() * 0.75f, 0);

		if (entity.getStartLandingTicks() > 0 && entity.getLandingDirection() != Direction.UP) {
			float landPercentage = (float)MathHelper.clamp(entity.tickCount - entity.getStartLandingTicks(), 0, 10) / 10;
			float rotAmount = 90 * landPercentage;

			switch (entity.getLandingDirection()) {
				case NORTH:
					matrix.mulPose(new Quaternion(Vector3f.XP, -rotAmount, true));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case SOUTH:
					matrix.mulPose(new Quaternion(Vector3f.XP, -rotAmount, true));
					//matrix.rotate(new Quaternion(rotAmount * 2, 0, 1, 0);
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case EAST:
					matrix.mulPose(new Quaternion(Vector3f.XP, -rotAmount, true));
					//matrix.rotate(rotAmount, 0, 0, 1);
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				case WEST:
					matrix.mulPose(new Quaternion(Vector3f.XP, -rotAmount, true));
					matrix.mulPose(new Quaternion(Vector3f.ZP, -rotAmount, true));
					matrix.translate(0, entity.getBbWidth() * 0.65f * landPercentage,0);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public ResourceLocation getTextureLocation(MeganeuropsisEntity entity) {
		return texture;
	}
}
