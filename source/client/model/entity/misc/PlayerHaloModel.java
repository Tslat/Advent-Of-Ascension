package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PlayerHaloModel extends PlayerModel<AbstractClientPlayerEntity> {
	public ModelRenderer halo;

	public PlayerHaloModel() {
		super(0, false);

		texWidth = 1;
		texHeight = 1;

		halo = new ModelRenderer(this, 0, 0);
		halo.setPos(0.0F, 0.0F, 0.0F);
		halo.addBox(-1.0F, -11.5f, -3.0F, 2, 1, 1, 0.0F);
		halo.addBox(-1.0F, -11.5f, 2.0F, 2, 1, 1, 0.0F);
		halo.addBox(-1.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		halo.addBox(-1.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		halo.addBox(-2.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		halo.addBox(-2.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		halo.addBox(-2.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		halo.addBox(-2.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		halo.addBox(-3.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
		halo.addBox(0.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		halo.addBox(0.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		halo.addBox(1.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		halo.addBox(1.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		halo.addBox(1.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		halo.addBox(1.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		halo.addBox(2.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		halo.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(AbstractClientPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
}
