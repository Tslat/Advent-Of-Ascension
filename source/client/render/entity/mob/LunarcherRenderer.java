package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.client.model.entity.misc.LunarcherModel;
import net.tslat.aoa3.common.registration.AoAEntities;

@OnlyIn(Dist.CLIENT)
public class LunarcherRenderer extends BipedRenderer<FlyingEntity, LunarcherModel<FlyingEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/mobs/lunalus/lunarcher.png");

	public LunarcherRenderer(EntityRendererManager renderManager) {
		super(renderManager, new LunarcherModel(), AoAEntities.Mobs.LUNARCHER.get().getWidth() / 3f);

		addLayer(new HeldItemLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(FlyingEntity entity) {
		return texture;
	}
}