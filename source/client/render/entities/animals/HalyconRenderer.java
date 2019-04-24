package net.tslat.aoa3.client.render.entities.animals;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.animals.ModelHalycon;
import net.tslat.aoa3.entity.animals.EntityHalycon;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class HalyconRenderer extends RenderLiving<EntityHalycon> {
	private final ResourceLocation texture;

	public HalyconRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelHalycon(), EntityHalycon.entityWidth / 3);
		texture = resource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityHalycon entity) {
		return texture;
	}
}