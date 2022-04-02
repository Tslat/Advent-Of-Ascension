package net.tslat.aoa3.client.model.entity.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.world.entity.Entity;

public class CobblestoneProjectileModel extends EntityModel<Entity> {
	private final ModelPart block;

	public CobblestoneProjectileModel(ModelPart meshDefRoot) {
		this.block = meshDefRoot.getChild("root");
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();

		meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create()
				.mirror().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, new CubeDeformation(-2.0f)),
				PartPose.offset(0, 16, 0));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		block.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		block.xRot += limbSwingAmount * 0.03f + 1;
		block.yRot += limbSwingAmount * 0.03f + 1;
		block.zRot += limbSwingAmount * 0.03f + 1;
	}
}
