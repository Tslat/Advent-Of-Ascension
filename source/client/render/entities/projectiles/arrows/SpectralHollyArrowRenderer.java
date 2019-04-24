package net.tslat.aoa3.client.render.entities.projectiles.arrows;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class SpectralHollyArrowRenderer extends HollyArrowRenderer {
	private final ResourceLocation texture;

	public SpectralHollyArrowRenderer(RenderManager renderManagerIn, final ResourceLocation textureResource) {
		super(renderManagerIn, textureResource);
		texture = textureResource;
	}

	@Override
	public void doRender(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks){
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
