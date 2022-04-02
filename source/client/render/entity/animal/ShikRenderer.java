package net.tslat.aoa3.client.render.entity.animal;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.animal.ShikEntity;

public class ShikRenderer extends MobRenderer<ShikEntity, EntityModel<ShikEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/passive/shik.png");

	public ShikRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, null/*new ShikModel()*/, AoAAnimals.SHIK.get().getWidth() / 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(ShikEntity entity) {
		return texture;
	}
}
