package net.tslat.aoa3.client.model.entity.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CobblestoneProjectileModel extends EntityModel<Entity> {
	private final ModelRenderer block;

	public CobblestoneProjectileModel() {
		textureWidth = 64;
		textureHeight = 32;

		block = new ModelRenderer(this);
		block.setRotationPoint(0.0F, 16.0F, 0.0F);
		block.setTextureOffset(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, -2.0F, true);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		block.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		block.rotateAngleX += limbSwingAmount * 0.03f + 1;
		block.rotateAngleY += limbSwingAmount * 0.03f + 1;
		block.rotateAngleZ += limbSwingAmount * 0.03f + 1;
	}
}
