package net.tslat.aoa3.client.render.entities.mobs.lunalus;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entities.misc.ModelFlyingBipedShooting;
import net.tslat.aoa3.entity.mobs.lunalus.EntityLunarcher;

public class LunarcherRenderer extends RenderBiped<EntityLunarcher> {
	private final ResourceLocation texture;

	public LunarcherRenderer(RenderManager renderManagerIn, ResourceLocation texture) {
		super(renderManagerIn, new ModelFlyingBipedShooting(), EntityLunarcher.entityWidth / 3);
		this.addLayer(new LayerHeldItem(this));
		this.texture = texture;
	}

	public void transformHeldFull3DItemLayer(){
		GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLunarcher entity) {
		return texture;
	}
}
