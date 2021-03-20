package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.immortallis.UriohModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;

public class UriohRenderer extends AoAMobRenderer {
	private static final float shadowScale = AoAEntities.Mobs.URIOH.get().getWidth() / 3f;
	public UriohRenderer(EntityRendererManager renderManager) {
		super(renderManager, new UriohModel(), shadowScale, 1.0f, new ResourceLocation("aoa3", "textures/entities/mobs/immortallis/urioh.png"));
	}

	@Override
	protected void scale(MobEntity entity, MatrixStack matrix, float partialTicks) {
		float scale = Math.max(0.1f, entity.getHealth() / entity.getMaxHealth());

		matrix.scale(scale, scale, scale);
		shadowRadius = shadowScale * scale;
	}
}