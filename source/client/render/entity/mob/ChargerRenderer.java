package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.overworld.ChargerModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;

public class ChargerRenderer extends AoAMobRenderer {
	private final ResourceLocation texture;

	public ChargerRenderer(EntityRendererManager renderManager, final ResourceLocation resource) {
		super(renderManager, new ChargerModel(), AoAEntities.Mobs.CHARGER.get().getWidth() / 3f, 1, resource);

		texture = ChargerModel.getChargerTexture((ChargerModel)model, resource);
	}

	public ResourceLocation getTexture() {
		return texture;
	}
}