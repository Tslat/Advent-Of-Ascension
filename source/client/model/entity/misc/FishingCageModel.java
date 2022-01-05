package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FishingCageModel extends EntityModel<Entity> {
	private final ModelRenderer bone;

	public FishingCageModel() {
		texWidth = 64;
		texHeight = 32;

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 24.0F, 0.0F);
		bone.texOffs(0, 0).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 13.0F, 0.0F, false);
		bone.texOffs(33, 0).addBox(-4.0F, -0.5F, -8.0F, 8.0F, 0.0F, 3.0F, 0.0F, false);
		bone.texOffs(0, 14).addBox(4.0F, -7.0F, -4.0F, 1.0F, 1.0F, 11.0F, 0.0F, true);
		bone.texOffs(29, 14).addBox(-4.0F, -7.0F, 7.0F, 8.0F, 1.0F, 1.0F, 0.0F, true);
		bone.texOffs(13, 14).addBox(-4.0F, -9.0F, 7.5F, 8.0F, 8.0F, 0.0F, 0.0F, true);
		bone.texOffs(0, 14).addBox(-5.0F, -7.0F, -4.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone.texOffs(0, 6).addBox(4.0F, -7.0F, -5.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 6).addBox(-5.0F, -7.0F, -5.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone.texOffs(33, -4).addBox(-4.5F, -7.0F, -4.0F, 0.0F, 6.0F, 11.0F, 0.0F, false);
		bone.texOffs(33, -4).addBox(4.5F, -7.0F, -4.0F, 0.0F, 6.0F, 11.0F, 0.0F, false);
		bone.texOffs(0, 6).addBox(4.0F, -7.0F, 7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-2.1716F, -9.83F, 6.99F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-0.8284F, -9.8284F, 7.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-2.1716F, -9.8284F, -5.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 0).addBox(-0.8284F, -9.83F, -4.98F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(0, 14).addBox(1.1716F, -9.8284F, -4.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone.texOffs(0, 14).addBox(-2.1716F, -9.8284F, -4.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone.texOffs(-11, 14).addBox(-2.0F, -9.3284F, -4.0F, 4.0F, 0.0F, 11.0F, 0.0F, false);
		bone.texOffs(0, 6).addBox(-5.0F, -7.0F, 7.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.7854F);
		bone3.texOffs(4, 8).addBox(7.4853F, -5.4142F, 7.01F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		bone3.texOffs(33, -8).addBox(8.0853F, -5.7142F, -3.99F, 0.0F, 4.0F, 11.0F, 0.0F, true);
		bone3.texOffs(4, 8).addBox(7.4853F, -5.4142F, -4.99F, 1.0F, 4.0F, 1.0F, 0.0F, true);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.texOffs(4, 8).addBox(-8.4853F, -5.4142F, 7.01F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(33, -8).addBox(-8.0853F, -5.7142F, -3.99F, 0.0F, 4.0F, 11.0F, 0.0F, false);
		bone2.texOffs(4, 8).addBox(-8.4853F, -5.4142F, -4.99F, 1.0F, 4.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
