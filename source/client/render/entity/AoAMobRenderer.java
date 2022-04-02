package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class AoAMobRenderer extends MobRenderer<Mob, EntityModel<Mob>> {
	private final ResourceLocation texture;
	private final float scale;

	public AoAMobRenderer(EntityRendererProvider.Context renderManager, EntityModel<? extends Mob> model, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, (EntityModel<Mob>)model, shadowSize);

		this.model = getModel();
		this.texture = texture;
		this.scale = scale;
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		matrix.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return texture;
	}
}
