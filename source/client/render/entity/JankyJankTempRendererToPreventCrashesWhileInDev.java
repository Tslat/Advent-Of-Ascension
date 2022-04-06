package net.tslat.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class JankyJankTempRendererToPreventCrashesWhileInDev extends EntityRenderer<Entity> {
	public JankyJankTempRendererToPreventCrashesWhileInDev(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ResourceLocation getTextureLocation(Entity entity) {
		return null;
	}
}
