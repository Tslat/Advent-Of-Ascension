package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityParalyzerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class ParalyzerShotRenderer extends Render<EntityParalyzerShot> {
	private final ResourceLocation texture;

	public ParalyzerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityParalyzerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 8).create();
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 8, 0.25f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityParalyzerShot entity) {
		return texture;
	}
}
