package net.tslat.aoa3.client.render.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.model.entity.animal.MeganeuropsisModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.animal.MeganeuropsisEntity;

public class MeganeuropsisRenderer extends MobRenderer<MeganeuropsisEntity, EntityModel<MeganeuropsisEntity>> {
	private static final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/passive/meganeuropsis.png");
	public MeganeuropsisRenderer(EntityRendererManager renderManager) {
		super(renderManager, new MeganeuropsisModel(), AoAEntities.Animals.MEGANEUROPSIS.get().getWidth() / 3f);
	}

	@Override
	protected void preRenderCallback(MeganeuropsisEntity entity, MatrixStack matrix, float partialTicks) {
		if (entity.isLanded() && entity.isPassenger())
			matrix.translate(0, -entity.getHeight() * 0.75f, 0);

		if (entity.getStartLandingTicks() > 0 && entity.getLandingDirection() != Direction.UP) {
			float landPercentage = (float)MathHelper.clamp(entity.ticksExisted - entity.getStartLandingTicks(), 0, 10) / 10;
			float rotAmount = 90 * landPercentage;

			switch (entity.getLandingDirection()) {
				case NORTH:
					matrix.rotate(new Quaternion(Vector3f.XP, -rotAmount, true));
					matrix.translate(0, entity.getWidth() * 0.65f * landPercentage,0);
					break;
				case SOUTH:
					matrix.rotate(new Quaternion(Vector3f.XP, -rotAmount, true));
					//matrix.rotate(new Quaternion(rotAmount * 2, 0, 1, 0);
					matrix.translate(0, entity.getWidth() * 0.65f * landPercentage,0);
					break;
				case EAST:
					matrix.rotate(new Quaternion(Vector3f.XP, -rotAmount, true));
					//matrix.rotate(rotAmount, 0, 0, 1);
					matrix.translate(0, entity.getWidth() * 0.65f * landPercentage,0);
					break;
				case WEST:
					matrix.rotate(new Quaternion(Vector3f.XP, -rotAmount, true));
					matrix.rotate(new Quaternion(Vector3f.ZP, -rotAmount, true));
					matrix.translate(0, entity.getWidth() * 0.65f * landPercentage,0);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public ResourceLocation getEntityTexture(MeganeuropsisEntity entity) {
		return texture;
	}
}
