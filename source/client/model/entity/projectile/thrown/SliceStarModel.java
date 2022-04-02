package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;

public class SliceStarModel extends EntityModel<BaseBullet> {
	private final ModelPart root;

	public SliceStarModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition rootPart = meshDefinition.getRoot();

		PartDefinition root = rootPart.addOrReplaceChild("root", CubeListBuilder.create()
						.texOffs(8, 2).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.1f)),
				PartPose.offset(0, 24, 0));

		root.addOrReplaceChild("blades", CubeListBuilder.create()
						.mirror().texOffs(0, 7).addBox(-2.75F, -1.0F, -0.25F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.25f))
						.mirror(false).texOffs(0, 0).addBox(-0.25F, -1.0F, -1.75F, 3.0F, 1.0F, 2.0F, new CubeDeformation(-0.25f))
						.mirror(false).texOffs(0, 3).addBox(-1.75F, -1.0F, -2.75F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.25f))
						.mirror().texOffs(0, 10).addBox(-0.25F, -1.0F, -0.25F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.25f)),
				PartPose.offsetAndRotation(0, 0.5f, 0, 0, -0.7854f, 0));

		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	@Override
	public void setupAnim(BaseBullet sliceStar, float pLimbSwing, float pLimbSwingAmount, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (sliceStar.getDeltaMovement().x() != 0 || sliceStar.getDeltaMovement().y() != 0 || sliceStar.getDeltaMovement().z() != 0) {
			root.yRot = Mth.lerp(pLimbSwingAmount, tickAge - 1 % 360, tickAge % 360);
		}
		else {
			root.yRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}