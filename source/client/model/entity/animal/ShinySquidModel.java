package net.tslat.aoa3.client.model.entity.animal;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;
import net.tslat.aoa3.content.entity.animal.ShinySquidEntity;

import java.util.Arrays;

public class ShinySquidModel extends HierarchicalModel<ShinySquidEntity> {
	private final ModelPart[] tentacles = new ModelPart[8];
	private final ModelPart root;

	public ShinySquidModel(ModelPart root) {
		this.root = root;

		Arrays.setAll(this.tentacles, num -> root.getChild("tentacle" + num));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition rootPart = meshDefinition.getRoot();
		CubeListBuilder cubesBuilder = FullbrightCubes.create().texOffs(48, 0).addGlowBox(-1, 0, -1, 2, 18, 2);

		rootPart.addOrReplaceChild("body", FullbrightCubes.create().texOffs(0, 0).addGlowBox(-6, -8, -6, 12, 16, 12), PartPose.offset(0, 8, 0));


		for(int i = 0; i < 8; ++i) {
			double angle = (double)i * Math.PI / 4d;

			rootPart.addOrReplaceChild("tentacle" + i, cubesBuilder, PartPose.offsetAndRotation((float)Math.cos(angle) * 5f, 15.0F, (float)Math.sin(angle) * 5f, 0.0F, (float)((double)i * Math.PI * -2d / 8d + (Math.PI / 2d)), 0.0F));
		}

		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(ShinySquidEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		for(ModelPart modelpart : this.tentacles) {
			modelpart.xRot = ageInTicks;
		}
	}
}
