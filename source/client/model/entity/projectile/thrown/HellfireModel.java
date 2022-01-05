package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.object.entity.projectile.thrown.HellfireEntity;

public class HellfireModel extends EntityModel<HellfireEntity> {
	private final ModelRenderer part;

	public HellfireModel() {
		texWidth = 32;
		texHeight = 16;

		part = new ModelRenderer(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 0).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		part.texOffs(0, 9).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(HellfireEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		part.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}