package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.RockRiderModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.RockRiderEntity;

import javax.annotation.Nullable;

public class RockRiderRenderer extends MobRenderer<RockRiderEntity, EntityModel<RockRiderEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/rockrider/rock_rider.png");
	private static final ResourceLocation ALTERNATE_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/rockrider/rock_rider_alternate.png");

	public RockRiderRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RockRiderModel(), AoAEntities.Mobs.ROCK_RIDER.get().getWidth() / 3);
	}

	@Override
	protected void preRenderCallback(RockRiderEntity rockRider, MatrixStack matrix, float partialTicks) {
		matrix.scale(1.5f, 1.5f, 1.5f);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(RockRiderEntity corallus) {
		return corallus.isAlternateForm() ? ALTERNATE_TEXTURE : TEXTURE;
	}
}