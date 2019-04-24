package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXLastingFluffyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityRainbowShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class RainbowShotRenderer extends Render<EntityRainbowShot> {
	private final ResourceLocation texture;

	public RainbowShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityRainbowShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 1.0, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY + 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 0.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 1.0, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 8).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY - 1.5, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 8).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityRainbowShot entity) {
		return texture;
	}
}
