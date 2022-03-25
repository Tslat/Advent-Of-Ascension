package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.content.entity.projectile.thrown.GooBallEntity;

public class GooBallModel extends EntityModel<GooBallEntity> {
	private final ModelRenderer part;

	public GooBallModel() {
		texWidth = 32;
		texHeight = 16;

		part = new ModelRenderer(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		part.texOffs(0, 0).addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
		part.texOffs(16, 2).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GooBallEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		part.yRot = MathHelper.lerp(pLimbSwingAmount, pAgeInTicks - 1 % 360, pAgeInTicks % 360);
		part.xRot = MathHelper.lerp(pLimbSwingAmount, pAgeInTicks % 360, pAgeInTicks + 1 % 360);
		part.zRot = MathHelper.lerp(pLimbSwingAmount, pAgeInTicks + 1 % 360, pAgeInTicks + 2 % 360);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		part.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}