package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityReeferShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class ReeferShotRenderer extends Render<EntityReeferShot> {
	private final ResourceLocation texture;


	public ReeferShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityReeferShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			if (entity.toggle1) {
				entity.yOffset1 += 0.12;

				if (entity.yOffset1 >= 3.0f)
					entity.toggle1 = !entity.toggle1;
			}
			if (!entity.toggle1) {
				entity.yOffset1 -= 0.12;

				if (entity.yOffset1 <= -3.0f)
					entity.toggle1 = !entity.toggle1;
			}

			if (entity.toggle2) {
				entity.yOffset2 += 0.12;

				if (entity.yOffset2 >= 3.0f)
					entity.toggle2 = !entity.toggle2;
			}
			if (!entity.toggle2) {
				entity.yOffset2 -= 0.12;

				if (entity.yOffset2 <= -3.0f)
					entity.toggle2 = !entity.toggle2;
			}

			for (int j = 0; j < 3; j++) {
				new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY + entity.yOffset1, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 25).create();
				new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY + entity.yOffset2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 25).create();
			}
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityReeferShot entity) {
		return texture;
	}
}
