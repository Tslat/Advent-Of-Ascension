package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;

public class GrenadeModel extends EntityModel<BaseBullet> {
	private final ModelRenderer part;

	public GrenadeModel() {
		texWidth = 16;
		texHeight = 16;

		part = new ModelRenderer(this);
		part.setPos(0.0F, 24.0F, 0.0F);
		part.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		part.texOffs(0, 12).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		part.texOffs(0, 6).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		part.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BaseBullet grenade, float pLimbSwing, float partialTicks, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (grenade.getDeltaMovement().x() != 0 || grenade.getDeltaMovement().y() != 0 || grenade.getDeltaMovement().z() != 0) {
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
}