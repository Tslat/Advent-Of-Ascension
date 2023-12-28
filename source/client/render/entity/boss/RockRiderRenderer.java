/*
package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import net.tslat.aoa3.content.entity.boss.RockRiderEntity;


public class RockRiderRenderer extends MobRenderer<RockRiderEntity, EntityModel<RockRiderEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/rockrider/rock_rider.png");
	private static final ResourceLocation ALTERNATE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/rockrider/rock_rider_alternate.png");

	public RockRiderRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RockRiderModel(), AoAMobs.ROCK_RIDER.get().getWidth() / 3);
	}

	@Override
	protected void scale(RockRiderEntity rockRider, PoseStack matrix, float partialTicks) {
		matrix.scale(1.5f, 1.5f, 1.5f);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(RockRiderEntity corallus) {
		return corallus.isAlternateForm() ? ALTERNATE_TEXTURE : TEXTURE;
	}
}*/
