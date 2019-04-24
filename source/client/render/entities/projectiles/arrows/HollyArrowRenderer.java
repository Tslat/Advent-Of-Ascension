package net.tslat.aoa3.client.render.entities.projectiles.arrows;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HollyArrowRenderer extends RenderArrow {
	private final ResourceLocation texture;

	public HollyArrowRenderer(RenderManager renderManagerIn, final ResourceLocation textureResource) {
		super(renderManagerIn);
		texture = textureResource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
