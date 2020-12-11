package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class FriendlyCreeperChargeRenderLayer extends LayerRenderer<MobEntity, EntityModel<MobEntity>> {
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final EntityModel<MobEntity> expandedModel;

	public FriendlyCreeperChargeRenderLayer(LivingRenderer<MobEntity, EntityModel<MobEntity>> entityRenderer, EntityModel<MobEntity> model) {
		super(entityRenderer);

		this.expandedModel = model;
	}

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLightIn, MobEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float netHeadYaw, float headPitch) {
		if (entity.isInvulnerable()) {
			float ageOffset = entity.ticksExisted + partialTicks;
			EntityModel<MobEntity> model = this.getEntityModel();

			model.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
			getEntityModel().copyModelAttributesTo(model);

			IVertexBuilder ivertexbuilder = buffer.getBuffer(RenderType.getEnergySwirl(getEntityTexture(entity), getAuraU(ageOffset), ageOffset * 0.01F));

			model.setRotationAngles(entity, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
			model.render(matrix, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
		}
	}

	public float getAuraU(float ageOffset) {
		return ageOffset * 0.01f;
	}

	@Override
	public EntityModel<MobEntity> getEntityModel() {
		return expandedModel;
	}

	@Override
	protected ResourceLocation getEntityTexture(MobEntity entityIn) {
		return texture;
	}
}