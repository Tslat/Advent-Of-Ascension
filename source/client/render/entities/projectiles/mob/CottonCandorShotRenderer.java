package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityCottonCandorShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class CottonCandorShotRenderer extends Render<EntityCottonCandorShot> {
	private final ResourceLocation texture;

	public CottonCandorShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityCottonCandorShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5).create();
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.PINK, 25).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCottonCandorShot entity) {
		return texture;
	}
}
