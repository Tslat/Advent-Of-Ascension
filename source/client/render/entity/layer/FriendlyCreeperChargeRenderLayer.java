/*
package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class FriendlyCreeperChargeRenderLayer extends LayerRenderer<Mob, EntityModel<Mob>> {
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final EntityModel<Mob> expandedModel;

	public FriendlyCreeperChargeRenderLayer(LivingRenderer<Mob, EntityModel<Mob>> entityRenderer, EntityModel<Mob> model) {
		super(entityRenderer);

		this.expandedModel = model;
	}

	@Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLightIn, Mob entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float netHeadYaw, float headPitch) {
		if (entity.isInvulnerable()) {
			float ageOffset = entity.tickCount + partialTicks;
			EntityModel<Mob> model = this.getParentModel();

			model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
			getParentModel().copyPropertiesTo(model);

			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.energySwirl(getTextureLocation(entity), getAuraU(ageOffset), ageOffset * 0.01F));

			model.setupAnim(entity, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
			model.renderToBuffer(matrix, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
		}
	}

	public float getAuraU(float ageOffset) {
		return ageOffset * 0.01f;
	}

	@Override
	public EntityModel<Mob> getParentModel() {
		return expandedModel;
	}

	@Override
	protected ResourceLocation getTextureLocation(Mob entityIn) {
		return texture;
	}
}*/
