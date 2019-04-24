package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityOrbocron;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class OrbocronRenderer extends Render<EntityOrbocron> {
	private final ResourceLocation texture;

	public OrbocronRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityOrbocron entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLUE, 8).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityOrbocron entity) {
		return texture;
	}
}
