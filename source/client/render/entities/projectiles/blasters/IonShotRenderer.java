package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityIonShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class IonShotRenderer extends Render<EntityIonShot> {
	private final ResourceLocation texture;

	public IonShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityIonShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY + 2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 8).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 8).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY - 2, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 8).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityIonShot entity) {
		return texture;
	}
}
