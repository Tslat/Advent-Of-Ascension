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
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;

public class GrenadeModel extends EntityModel<BaseBullet> {
	private final ModelPart root;

	public GrenadeModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();

		meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-1, -2, -1, 2, 4, 2)
						.texOffs(0, 12).addBox(-2, -1, -1, 4, 2, 2)
						.texOffs(0, 6).addBox(-1, -1, -2, 2, 2, 4)
						.texOffs(0, 0).addBox(-1, -2, -1, 2, 1, 2),
				PartPose.offset(0, 24, 0));

		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	@Override
	public void setupAnim(BaseBullet grenade, float pLimbSwing, float partialTicks, float tickAge, float pNetHeadYaw, float pHeadPitch) {
		if (grenade.getDeltaMovement().x() != 0 || grenade.getDeltaMovement().y() != 0 || grenade.getDeltaMovement().z() != 0) {
			root.yRot = Mth.lerp(partialTicks, tickAge - 1 % 360, tickAge % 360);
			root.xRot = Mth.lerp(partialTicks, tickAge % 360, tickAge + 1 % 360);
			root.zRot = Mth.lerp(partialTicks, tickAge + 1 % 360, tickAge + 2 % 360);
		}
		else {
			root.xRot = 0;
			root.yRot = 0;
			root.zRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}