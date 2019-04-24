package net.tslat.aoa3.client.render.entities.projectiles.misc;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.misc.EntitySelyanSticklerStuck;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class SelyanSticklerStuckRenderer extends Render<EntitySelyanSticklerStuck> {
	private final ResourceLocation texture;

	public SelyanSticklerStuckRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntitySelyanSticklerStuck entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 7; i++) {
			new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 5).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySelyanSticklerStuck entity) {
		return texture;
	}
}
