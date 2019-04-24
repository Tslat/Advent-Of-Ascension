package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityGrawShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class GrawShotRenderer extends Render<EntityGrawShot> {
	private final ResourceLocation texture;

	public GrawShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityGrawShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 8).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityGrawShot entity) {
		return texture;
	}
}
