package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.projectile.CorallusShotModel;
import net.tslat.aoa3.entity.projectile.mob.CorallusShotEntity;

import javax.annotation.Nullable;

public class CorallusShotRenderer extends LivingRenderer<CorallusShotEntity, EntityModel<CorallusShotEntity>> {
	private final ResourceLocation TEXTURE;

	public CorallusShotRenderer(EntityRendererManager renderManager, final ResourceLocation resource) {
		super(renderManager, new CorallusShotModel(), 0);
		TEXTURE = resource;
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(CorallusShotEntity entity) {
		return TEXTURE;
	}
}