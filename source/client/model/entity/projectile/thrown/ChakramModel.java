package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;

public class ChakramModel extends EntityModel<BaseBullet> {
	private final ModelPart root;

	public ChakramModel(ModelPart modelDefRoot) {
		this.root = modelDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition rootPart = meshDefinition.getRoot();

		PartDefinition root = rootPart.addOrReplaceChild("root", CubeListBuilder.create()
				.texOffs(0, 8).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F),
				PartPose.offset(0, 24, 0));

		root.addOrReplaceChild("blades", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-1.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.1f))
						.mirror().texOffs(0, 0).addBox(0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.1f))
						.mirror().texOffs(0, 6).addBox(-2.5F, -0.5F, -1.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.1f))
						.mirror().texOffs(0, 6).addBox(-2.5F, -0.5F, 0.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.1f)),
				PartPose.rotation(0, -0.7854f, 0));

		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	@Override
	public void setupAnim(BaseBullet chakram, float pLimbSwing, float pLimbSwingAmount, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (chakram.getDeltaMovement().x() != 0 || chakram.getDeltaMovement().y() != 0 || chakram.getDeltaMovement().z() != 0) {
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