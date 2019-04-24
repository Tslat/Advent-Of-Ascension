package net.tslat.aoa3.client.render.entities.projectiles.staff;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.staff.EntityPowerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class PowerShotRenderer extends Render<EntityPowerShot> {
	private final ResourceLocation texture;

	public PowerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityPowerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 3).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityPowerShot entity) {
		return texture;
	}
}
