package net.tslat.aoa3.client.render.entities.mobs.misc;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.model.entities.misc.ModelThinBipedShooting;
import net.tslat.aoa3.entity.mobs.misc.EntitySeaSkeleton;

@SideOnly(Side.CLIENT)
public class SeaSkeletonRenderer extends RenderBiped<EntitySeaSkeleton> {
	private final ResourceLocation texture;

	public SeaSkeletonRenderer(RenderManager renderManagerIn, ResourceLocation texture) {
		super(renderManagerIn, new ModelThinBipedShooting(), EntitySeaSkeleton.entityWidth / 3);
		this.addLayer(new LayerHeldItem(this));
		this.texture = texture;
	}

	public void transformHeldFull3DItemLayer(){
		GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySeaSkeleton entity) {
		return texture;
	}
}
