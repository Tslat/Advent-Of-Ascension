package net.tslat.aoa3.client.render.entities.mobs.nether;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entities.misc.ModelBipedShooting;
import net.tslat.aoa3.entity.mobs.nether.EntitySkeletalCowman;

public class SkeletalCowmanRenderer extends RenderBiped<EntitySkeletalCowman> {
	private final ResourceLocation texture;

	public SkeletalCowmanRenderer(RenderManager renderManagerIn, ResourceLocation texture) {
		super(renderManagerIn, new ModelBipedShooting(), EntitySkeletalCowman.entityWidth / 3);
		this.addLayer(new LayerHeldItem(this));
		this.texture = texture;
	}

	public void transformHeldFull3DItemLayer(){
		GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySkeletalCowman entity) {
		return texture;
	}
}
