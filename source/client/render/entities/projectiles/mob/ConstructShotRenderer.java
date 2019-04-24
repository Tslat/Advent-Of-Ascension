package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityConstructShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class ConstructShotRenderer extends Render<EntityConstructShot> {
	private final ResourceLocation texture;

	public ConstructShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityConstructShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 5).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityConstructShot entity) {
		return texture;
	}
}
