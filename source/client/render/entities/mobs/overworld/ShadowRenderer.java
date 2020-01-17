package net.tslat.aoa3.client.render.entities.mobs.overworld;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.misc.ModelNothing;
import net.tslat.aoa3.entity.mobs.overworld.EntityShadow;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ShadowRenderer extends RenderLiving<EntityShadow> {
	private final ResourceLocation texture;

	public ShadowRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ModelNothing(), EntityShadow.entityWidth / 3);
		texture = resource;
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityShadow entity) {
		return null;
	}
}