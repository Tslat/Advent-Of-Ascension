package net.tslat.aoa3.client.render.entities.projectiles.mob;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreepTube;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class CreepTubeRenderer extends Render<EntityCreepTube> {
	private final ResourceLocation texture;

	public CreepTubeRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityCreepTube entity, double x, double y, double z, float entityYaw, float partialTicks) {
		new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.BLACK, 3).create();
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityCreepTube entity) {
		return texture;
	}
}
