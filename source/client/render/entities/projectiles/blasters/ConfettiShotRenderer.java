package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFluffyRainbowParticle;
import net.tslat.aoa3.entity.projectiles.blaster.EntityConfettiShot;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ConfettiShotRenderer extends Render<EntityConfettiShot> {
	private final ResourceLocation texture;

	public ConfettiShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityConfettiShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			new FXFluffyRainbowParticle(entity.world, entity.posX, entity.posY, entity.posZ, 0, -0.05, 0,3, 0.15f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityConfettiShot entity) {
		return texture;
	}
}
