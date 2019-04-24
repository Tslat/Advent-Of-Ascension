package net.tslat.aoa3.client.render.entities.projectiles.cannonshots;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.cannon.EntityErebonSticklerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class ErebonSticklerShotRenderer extends Render<EntityErebonSticklerShot> {
	private final ResourceLocation texture;

	public ErebonSticklerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityErebonSticklerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 14; i++) {
			new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.RED, 65).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityErebonSticklerShot entity) {
		return texture;
	}
}
