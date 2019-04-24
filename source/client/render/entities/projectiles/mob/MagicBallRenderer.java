package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityMagicBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class MagicBallRenderer extends Render<EntityMagicBall> {
	private final ResourceLocation texture;

	public MagicBallRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityMagicBall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PURPLE, 5).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMagicBall entity) {
		return texture;
	}
}
