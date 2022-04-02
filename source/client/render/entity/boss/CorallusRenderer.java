/*
package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import javax.annotation.Nullable;

public class CorallusRenderer extends MobRenderer<CorallusEntity, EntityModel<CorallusEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/corallus/corallus.png");
	private static final ResourceLocation ENRAGED_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/corallus/corallus_enraged.png");

	public CorallusRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CorallusModel(), AoAMobs.CORALLUS.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CorallusEntity corallus) {
		return corallus.isEnraged() ? ENRAGED_TEXTURE : TEXTURE;
	}
}*/
