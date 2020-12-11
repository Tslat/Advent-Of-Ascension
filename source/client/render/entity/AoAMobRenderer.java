package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.overworld.ChargerModel;
import net.tslat.aoa3.config.AoAConfig;

public class AoAMobRenderer extends MobRenderer<MobEntity, EntityModel<MobEntity>> {
	private static final ChargerModel chargerModel = new ChargerModel();
	public static final ResourceLocation chargerTexture = ChargerModel.getChargerTexture(chargerModel, null);

	private final ResourceLocation texture;
	private final float scale;

	public AoAMobRenderer(EntityRendererManager renderManager, EntityModel<MobEntity> model, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, model, shadowSize);

		this.entityModel = getEntityModel();
		this.texture = texture;
		this.scale = scale;
	}

	@Override
	protected void preRenderCallback(MobEntity entity, MatrixStack matrix, float partialTicks) {
		matrix.scale(scale, scale, scale);
	}

	@Override
	public EntityModel<MobEntity> getEntityModel() {
		return AoAConfig.CLIENT.alwaysChargers.get() ? chargerModel : super.getEntityModel();
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		return AoAConfig.CLIENT.alwaysChargers.get() ? chargerTexture : texture;
	}
}
