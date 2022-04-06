package net.tslat.aoa3.client.model.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.content.entity.misc.FishingCageEntity;

public class FishingCageModel extends EntityModel<FishingCageEntity> {
	private final ModelPart root;

	public FishingCageModel(ModelPart meshDefRoot) {
		this.root = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(33, 0).addBox(-4.0F, -0.5F, -8.0F, 8.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).mirror().addBox(4.0F, -7.0F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(29, 14).mirror().addBox(-4.0F, -7.0F, 7.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(13, 14).mirror().addBox(-4.0F, -9.0F, 7.5F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 14).addBox(-5.0F, -7.0F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(4.0F, -7.0F, -5.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-5.0F, -7.0F, -5.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, -4).addBox(-4.5F, -7.0F, -4.0F, 0.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(33, -4).addBox(4.5F, -7.0F, -4.0F, 0.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(4.0F, -7.0F, 7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1716F, -9.83F, 6.99F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.8284F, -9.8284F, 7.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.1716F, -9.8284F, -5.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.8284F, -9.83F, -4.98F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(1.1716F, -9.8284F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 14).addBox(-2.1716F, -9.8284F, -4.0F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(-11, 14).addBox(-2.0F, -9.3284F, -4.0F, 4.0F, 0.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(0, 6).addBox(-5.0F, -7.0F, 7.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(4, 8).mirror().addBox(7.4853F, -5.4142F, 7.01F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(33, -8).mirror().addBox(8.0853F, -5.7142F, -3.99F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(4, 8).mirror().addBox(7.4853F, -5.4142F, -4.99F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(4, 8).addBox(-8.4853F, -5.4142F, 7.01F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, -8).addBox(-8.0853F, -5.7142F, -3.99F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
				.texOffs(4, 8).addBox(-8.4853F, -5.4142F, -4.99F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		this.root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}

	@Override
	public void setupAnim(FishingCageEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {}
}
