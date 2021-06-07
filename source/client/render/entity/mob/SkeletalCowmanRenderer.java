package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;

@OnlyIn(Dist.CLIENT)
public class SkeletalCowmanRenderer extends BipedRenderer<MonsterEntity, BipedModel<MonsterEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/mobs/nether/skeletal_cowman.png");

	public SkeletalCowmanRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BipedModel<MonsterEntity>(0), AoAEntities.Mobs.SKELETAL_COWMAN.get().getWidth() / 3f);
		addLayer(new HeldItemLayer<MonsterEntity, BipedModel<MonsterEntity>>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterEntity entity) {
		return texture;
	}
}