package net.tslat.aoa3.client.model.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.content.entity.misc.LottoTotemEntity;

public class LottoTotemModel extends EntityModel<LottoTotemEntity> {
	private final ModelPart root;
	private final ModelPart coin;

	public LottoTotemModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
		this.coin = meshDefRoot.getChild("coin");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 0).addBox(2.6569F, -8.0F, -2.8284F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(5.1569F, -2.0F, -2.8284F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-7.1569F, -2.0F, -2.8284F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(5.1569F, -2.0F, 1.8284F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-7.1569F, -2.0F, 1.8284F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 25).addBox(1.8284F, -2.0F, 5.1569F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 25).addBox(1.8284F, -2.0F, -7.1569F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 25).addBox(-2.8284F, -2.0F, 5.1569F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(22, 25).addBox(-2.8284F, -2.0F, -7.1569F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(2.6569F, -8.0F, -0.1716F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-2.8284F, -8.0F, 2.6569F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.8284F, -8.0F, -5.6569F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.1716F, -8.0F, 2.6569F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-0.1716F, -8.0F, -5.6569F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-5.6569F, -8.0F, -2.8284F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.6569F, -8.0F, -0.1716F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		bone3.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(26, 20).addBox(5.3315F, -2.788F, -2.8284F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 20).addBox(5.3315F, -2.788F, 1.8284F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));
		bone3.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(26, 20).addBox(-7.3315F, -2.788F, -2.8284F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 20).addBox(-7.3315F, -2.788F, 1.8284F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));
		bone3.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(18, 20).addBox(-2.8284F, -2.788F, -7.3315F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 20).addBox(1.8284F, -2.788F, -7.3315F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4363F, 0.0F, 0.0F));
		bone3.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(18, 20).addBox(-2.8284F, -2.788F, 5.3315F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 20).addBox(1.8284F, -2.788F, 5.3315F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4363F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition coin = partDefinition.addOrReplaceChild("coin", CubeListBuilder.create().texOffs(0, 27).addBox(-1.5F, -0.499F, 0.6F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(10, 27).addBox(-1.5F, -0.501F, -2.5962F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.5F, 0.0F, -1.5708F, 0.0F, 0.0F));

		coin.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(10, 27).addBox(-2.634F, -11.998F, -3.8301F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 27).addBox(-2.634F, -12.002F, -0.634F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 11.5F, 1.6F, 0.0F, -1.0472F, 0.0F));
		coin.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(0, 27).addBox(0.134F, -12.0F, 0.2321F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(10, 27).addBox(0.134F, -12.0F, -2.9641F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 11.5F, 1.6F, 0.0F, 1.0472F, 0.0F));

		return LayerDefinition.create(meshDefinition, 32, 32);
	}

	@Override
	public void setupAnim(LottoTotemEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		coin.yRot = (pAgeInTicks * 0.045f) % 360;
	}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		this.root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}
}
