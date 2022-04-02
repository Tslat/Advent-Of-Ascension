/*
package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.runandor.RuneTemplarModel;

import net.tslat.aoa3.content.entity.mob.runandor.templars.RuneTemplarEntity;

public class RuneTemplarRenderer extends MobRenderer<RuneTemplarEntity, EntityModel<RuneTemplarEntity>> {
	private final ResourceLocation texture;
	private final ResourceLocation disabledTexture;

	public RuneTemplarRenderer(EntityRendererProvider.Context renderManager, ResourceLocation texture, ResourceLocation disabledTexture) {
		super(renderManager, new RuneTemplarModel(), AoAMobs.BLUE_RUNE_TEMPLAR.get().getWidth() / 3f);

		this.texture = texture;
		this.disabledTexture = disabledTexture;
	}

	@Override
	public ResourceLocation getTextureLocation(RuneTemplarEntity entity) {
		return entity.isDisabled() ? disabledTexture : texture;
	}
}
*/
