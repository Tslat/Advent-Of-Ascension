package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.staff.EntityMeteorFall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class MeteorFallRenderer extends Render<EntityMeteorFall> {
	private final ResourceLocation texture;

	public MeteorFallRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityMeteorFall entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY , entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 3).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY - 0.3D , entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 3).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY - 0.6D , entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 3).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityMeteorFall entity) {
		return texture;
	}
}
