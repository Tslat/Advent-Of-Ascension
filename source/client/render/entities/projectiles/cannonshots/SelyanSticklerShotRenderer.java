package net.tslat.aoa3.client.render.entities.projectiles.cannonshots;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXFluffyTrail;
import net.tslat.aoa3.entity.projectiles.cannon.EntitySelyanSticklerShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class SelyanSticklerShotRenderer extends Render<EntitySelyanSticklerShot> {
	private final ResourceLocation texture;

	public SelyanSticklerShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntitySelyanSticklerShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 14; i++) {
			new FXFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.GREEN, 65, 1).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntitySelyanSticklerShot entity) {
		return texture;
	}
}
