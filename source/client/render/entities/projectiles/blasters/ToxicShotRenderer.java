package net.tslat.aoa3.client.render.entities.projectiles.blasters;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXLastingFluffyTrail;
import net.tslat.aoa3.entity.projectiles.blaster.EntityToxicShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ToxicShotRenderer extends Render<EntityToxicShot> {
	private final ResourceLocation texture;

	public ToxicShotRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityToxicShot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.TOXIC_GREEN, 8, 1).create();
			new FXLastingFluffyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.DARK_GRAY, 8, 0.5f).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityToxicShot entity) {
		return texture;
	}
}
