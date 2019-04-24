package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityWhiteBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class WhiteBallRenderer extends Render<EntityWhiteBall> {
	private final ResourceLocation texture;

	public WhiteBallRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityWhiteBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.WHITE, 5).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityWhiteBall entity) {
		return texture;
	}
}
