package net.tslat.aoa3.client.model.entity.projectile.thrown;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;
import net.tslat.aoa3.content.entity.projectile.thrown.GooBallEntity;

public class GooBallModel extends EntityModel<GooBallEntity> {
	private final ModelPart root;

	public GooBallModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();

		meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create()
						.texOffs(0, 8).addBox(-1.5F, -2.5F, -1.5F, 3.0F, 5.0F, 3.0F)
						.texOffs(0, 0).addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F)
						.texOffs(16, 2).addBox(-2.5F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F),
				PartPose.offset(0, 24, 0));

		return LayerDefinition.create(meshDefinition, 32, 16);
	}

	@Override
	public void setupAnim(GooBallEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		root.yRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks - 1 % 360, pAgeInTicks % 360);
		root.xRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks % 360, pAgeInTicks + 1 % 360);
		root.zRot = Mth.lerp(pLimbSwingAmount, pAgeInTicks + 1 % 360, pAgeInTicks + 2 % 360);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}