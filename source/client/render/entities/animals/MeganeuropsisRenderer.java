package net.tslat.aoa3.client.render.entities.animals;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.model.entities.passive.ModelMeganeuropsis;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.entity.passive.EntityMeganeuropsis;

public class MeganeuropsisRenderer extends AoAAnimalRenderer {
	public MeganeuropsisRenderer(RenderManager renderManager) {
		super(renderManager, new ModelMeganeuropsis(), EntityMeganeuropsis.entityWidth, new ResourceLocation("aoa3", "textures/entities/passive/meganeuropsis.png"));
	}

	@Override
	protected void preRenderCallback(AoAAnimal animal, float partialTickTime) {
		EntityMeganeuropsis meganeuropsis = (EntityMeganeuropsis)animal;

		if (meganeuropsis.isLanded() && meganeuropsis.isRiding())
			GlStateManager.translate(0, -animal.height * 0.75f, 0);

		if (meganeuropsis.getStartLandingTicks() > 0 && meganeuropsis.getLandingDirection() != EnumFacing.UP) {
			float landPercentage = (float)MathHelper.clamp(meganeuropsis.ticksExisted - meganeuropsis.getStartLandingTicks(), 0, 10) / 10;
			float rotAmount = 90 * landPercentage;

			switch (meganeuropsis.getLandingDirection()) {
				case NORTH:
					GlStateManager.rotate(-rotAmount, 1, 0, 0);
					GlStateManager.translate(0, meganeuropsis.width * 0.65f * landPercentage,0);
					break;
				case SOUTH:
					GlStateManager.rotate(-rotAmount, 1, 0, 0);
					//GlStateManager.rotate(rotAmount * 2, 0, 1, 0);
					GlStateManager.translate(0, meganeuropsis.width * 0.65f * landPercentage,0);
					break;
				case EAST:
					GlStateManager.rotate(-rotAmount, 1, 0, 0);
					//GlStateManager.rotate(rotAmount, 0, 0, 1);
					GlStateManager.translate(0, meganeuropsis.width * 0.65f * landPercentage,0);
					break;
				case WEST:
					GlStateManager.rotate(-rotAmount, 1, 0, 0);
					GlStateManager.rotate(-rotAmount, 0, 0, 1);
					GlStateManager.translate(0, meganeuropsis.width * 0.65f * landPercentage,0);
					break;
				default:
					break;
			}
		}
	}
}
