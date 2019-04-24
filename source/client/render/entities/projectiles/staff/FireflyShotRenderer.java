package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.staff.EntityFireflyShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class FireflyShotRenderer extends Render<EntityFireflyShot> {
	private final ResourceLocation texture;

	public FireflyShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityFireflyShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.ORANGE, 3).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityFireflyShot entity) {
		return texture;
	}
}
