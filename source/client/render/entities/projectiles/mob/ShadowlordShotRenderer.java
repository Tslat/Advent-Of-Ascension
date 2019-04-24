package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityShadowlordShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class ShadowlordShotRenderer extends Render<EntityShadowlordShot> {
	private final ResourceLocation texture;

	public ShadowlordShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityShadowlordShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			if (entity.toggle) {
				entity.counter++;

				if (entity.counter == 3)
					entity.toggle = false;
			}

			if (!entity.toggle) {
				entity.counter--;

				if (entity.counter == 0)
					entity.toggle = true;
			}
		}

		if (entity.toggle) {
			new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 8).create();
		}
		else {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLACK, 5).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityShadowlordShot entity) {
		return texture;
	}
}
