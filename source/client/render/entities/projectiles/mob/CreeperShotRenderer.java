package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreeperShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class CreeperShotRenderer extends Render<EntityCreeperShot> {
	private final ResourceLocation texture;

	public CreeperShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityCreeperShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 5).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCreeperShot entity) {
		return texture;
	}
}
