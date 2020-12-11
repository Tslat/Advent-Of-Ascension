package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.HydroliskModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.HydroliskEntity;

import javax.annotation.Nullable;

public class HydroliskRenderer extends MobRenderer<HydroliskEntity, EntityModel<HydroliskEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/hydrolisk/hydrolisk.png");
	private static final ResourceLocation SHIELDED_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/hydrolisk/hydrolisk_shielded.png");

	public HydroliskRenderer(EntityRendererManager renderManager) {
		super(renderManager, new HydroliskModel(), AoAEntities.Mobs.HYDROLISK.get().getWidth() / 3);
	}

	@Override
	protected void preRenderCallback(HydroliskEntity hydrolisk, MatrixStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(HydroliskEntity corallus) {
		return corallus.isShielded() ? SHIELDED_TEXTURE : TEXTURE;
	}
}