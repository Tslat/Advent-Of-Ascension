package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.content.entity.projectile.thrown.HellfireEntity;

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
	public void setupAnim(HellfireEntity hellfire, float pLimbSwing, float partialTicks, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (hellfire.getDeltaMovement().x() != 0 || hellfire.getDeltaMovement().y() != 0 || hellfire.getDeltaMovement().z() != 0) {
			part.yRot = MathHelper.lerp(partialTicks, tickAge - 1 % 360, tickAge % 360);
			part.xRot = MathHelper.lerp(partialTicks, tickAge % 360, tickAge + 1 % 360);
			part.zRot = MathHelper.lerp(partialTicks, tickAge + 1 % 360, tickAge + 2 % 360);
		}
		else {
			part.xRot = 0;
			part.yRot = 0;
			part.zRot = 0;
		}
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