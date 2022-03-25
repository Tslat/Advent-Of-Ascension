package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.content.entity.mob.creeponia.AoACreeponiaCreeper;

public class CustomCreeperChargeRenderLayer extends LayerRenderer<AoACreeponiaCreeper, EntityModel<AoACreeponiaCreeper>> {
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
	private final EntityModel<AoACreeponiaCreeper> model;

	public CustomCreeperChargeRenderLayer(IEntityRenderer<AoACreeponiaCreeper, EntityModel<AoACreeponiaCreeper>> renderer, EntityModel<AoACreeponiaCreeper> model) {
		super(renderer);

		this.model = model;
	}


	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight, AoACreeponiaCreeper entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isCharged()) {
			float renderTicks = (float)entity.tickCount + partialTicks;
			EntityModel<AoACreeponiaCreeper> model = getExpandedModel();

			model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
			getParentModel().copyPropertiesTo(model);

			IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.energySwirl(getLayerTexture(), getLayerUV(renderTicks), renderTicks * 0.01F));

			model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
		}
	}

	protected float getLayerUV(float uvBase) {
		return uvBase * 0.01f;
	}

	protected ResourceLocation getLayerTexture() {
		return texture;
	}

	protected EntityModel<AoACreeponiaCreeper> getExpandedModel() {
		return model;
	}
}