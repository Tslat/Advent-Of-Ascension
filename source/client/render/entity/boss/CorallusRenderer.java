package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.CorallusModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.boss.CorallusEntity;

import javax.annotation.Nullable;

public class CorallusRenderer extends MobRenderer<CorallusEntity, EntityModel<CorallusEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/corallus/corallus.png");
	private static final ResourceLocation ENRAGED_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/corallus/corallus_enraged.png");

	public CorallusRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CorallusModel(), AoAEntities.Mobs.CORALLUS.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CorallusEntity corallus) {
		return corallus.isEnraged() ? ENRAGED_TEXTURE : TEXTURE;
	}
}