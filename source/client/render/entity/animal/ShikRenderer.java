package net.tslat.aoa3.client.render.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.animal.ShikModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.animal.ShikEntity;

public class ShikRenderer extends MobRenderer<ShikEntity, EntityModel<ShikEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/passive/shik.png");

	public ShikRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ShikModel(), AoAEntities.Animals.SHIK.get().getWidth() / 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(ShikEntity entity) {
		return texture;
	}
}
