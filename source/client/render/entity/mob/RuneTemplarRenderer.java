package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.runandor.RuneTemplarModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.mob.runandor.templars.RuneTemplarEntity;

public class RuneTemplarRenderer extends MobRenderer<RuneTemplarEntity, EntityModel<RuneTemplarEntity>> {
	private final ResourceLocation texture;
	private final ResourceLocation disabledTexture;

	public RuneTemplarRenderer(EntityRendererManager renderManager, ResourceLocation texture, ResourceLocation disabledTexture) {
		super(renderManager, new RuneTemplarModel(), AoAEntities.Mobs.BLUE_RUNE_TEMPLAR.get().getWidth() / 3f);

		this.texture = texture;
		this.disabledTexture = disabledTexture;
	}

	@Override
	public ResourceLocation getEntityTexture(RuneTemplarEntity entity) {
		return entity.isDisabled() ? disabledTexture : texture;
	}
}
