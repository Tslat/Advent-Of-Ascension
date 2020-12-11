package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.client.model.entity.misc.ThinBipedShootingModel;
import net.tslat.aoa3.common.registration.AoAEntities;

@OnlyIn(Dist.CLIENT)
public class SeaSkeletonRenderer extends BipedRenderer<MobEntity, BipedModel<MobEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/mobs/misc/sea_skeleton.png");

	public SeaSkeletonRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ThinBipedShootingModel(), AoAEntities.Mobs.SEA_SKELETON.get().getWidth() / 3f);

		addLayer(new HeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		return texture;
	}
}