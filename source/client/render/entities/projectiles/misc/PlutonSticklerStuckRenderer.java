package net.tslat.aoa3.client.render.entities.projectiles.misc;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.fx.FXSwirlyTrail;
import net.tslat.aoa3.entity.projectiles.misc.EntityPlutonSticklerStuck;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class PlutonSticklerStuckRenderer extends Render<EntityPlutonSticklerStuck> {
	private final ResourceLocation texture;

	public PlutonSticklerStuckRenderer(final RenderManager manager, final ResourceLocation textureResource) {
		super(manager);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityPlutonSticklerStuck entity, double x, double y, double z, float entityYaw, float partialTicks) {
		for (int i = 0; i < 7; i++) {
			new FXSwirlyTrail(entity.world, entity.posX, entity.posY, entity.posZ, 0, 0, 0, Enums.RGBIntegers.YELLOW, 5, 1).create();
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityPlutonSticklerStuck entity) {
		return texture;
	}
}
