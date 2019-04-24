package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.fx.FXFluffyRainbowParticle;
import net.tslat.aoa3.entity.projectiles.blaster.EntityConfettiCluster;

import javax.annotation.Nullable;

public class ConfettiClusterRenderer extends Render<EntityConfettiCluster> {
	private final ResourceLocation texture;

	public ConfettiClusterRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityConfettiCluster entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 2; i++) {
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0, -0.05, 0,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, -0.05, -0.05, 0,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0.05, -0.05, 0,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, -0.05, -0.05, -0.05,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, -0.05, -0.05, 0.05,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0.05, -0.05, 0.05,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0.05, -0.05, -0.05,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0, -0.05, -0.05,3, 0.15f).create();
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0, -0.05, 0.05,3, 0.15f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityConfettiCluster entity) {
		return texture;
	}
}
