package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Function;

public class HelmOfTheRitualistTrimModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_ritualist_trim"), "main");

	public HelmOfTheRitualistTrimModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheRitualistTrimModel> modelFactory() {
		return modelSet -> new HelmOfTheRitualistTrimModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.25F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 21).addBox(-2.0F, -9.25F, -3.5F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(16, 12).addBox(-3.5F, -5.25F, 3.5F, 7.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-3.5F, -9.25F, 4.5F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 2).addBox(-1.5F, -6.5F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(0.5F, -2.75F, -4.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(24, 20).addBox(2.25F, -1.5F, -4.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(32, 9).addBox(3.25F, -3.75F, -4.5F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(24, 21).addBox(3.5F, -5.25F, -0.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(31, 21).mirror().addBox(-1.49F, -2.75F, -4.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(31, 21).addBox(0.49F, -2.75F, -4.75F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(24, 20).mirror().addBox(-3.25F, -1.5F, -4.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 24).mirror().addBox(-3.5F, -2.75F, -4.5F, 3.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(32, 9).mirror().addBox(-4.25F, -3.75F, -4.5F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(24, 21).mirror().addBox(-4.5F, -5.25F, -0.5F, 1.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition bone20 = root.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(16, 20).addBox(1.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -0.15F, -2.8F, 0.4363F, 0.0F, 0.0F));

		bone20.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(45, 18).addBox(2.0F, -9.2917F, 1.8536F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5873F, 7.125F, 0.3054F, 0.0F, 0.0F));

		PartDefinition bone5 = root.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		bone5.addOrReplaceChild("bone5_r1", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0F, -10.25F, -4.05F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5976F, -2.961F, -0.2618F, 0.0F, 0.0F));

		PartDefinition bone6 = bone5.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(37, 0).addBox(1.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.35F, -0.2F, 2.15F, 0.5236F, 0.1309F, 0.0F));

		bone6.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(42, 18).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -7.9127F, 6.625F, 0.3927F, 0.1745F, 0.0F));

		PartDefinition bone11 = bone5.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(37, 0).mirror().addBox(-3.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.35F, -0.2F, 2.15F, 0.5236F, -0.1309F, 0.0F));

		bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(42, 18).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -7.9127F, 6.625F, 0.3927F, -0.1745F, 0.0F));

		PartDefinition bone7 = bone5.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(35, 18).addBox(1.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7F, -0.2F, 4.65F, 0.5315F, -0.2095F, -0.1969F));

		bone7.addOrReplaceChild("bone21", CubeListBuilder.create().texOffs(42, 18).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -7.9127F, 5.625F, 0.5236F, -0.2182F, 0.0F));

		PartDefinition bone22 = bone5.addOrReplaceChild("bone22", CubeListBuilder.create().texOffs(35, 18).mirror().addBox(-3.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.7F, -0.2F, 4.65F, 0.5315F, 0.2095F, 0.1969F));

		bone22.addOrReplaceChild("bone23", CubeListBuilder.create().texOffs(42, 18).mirror().addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -7.9127F, 5.625F, 0.5236F, 0.2182F, 0.0F));

		PartDefinition bone3 = root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(27, 2).addBox(0.0F, -3.0F, 0.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.75F, -5.45F, -2.35F, 0.3491F, 0.3927F, 0.0F));
		PartDefinition bone4 = bone3.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(35, 18).addBox(1.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.25F, 4.0F, 0.5236F, 0.0F, 0.0F));

		bone4.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(42, 18).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -8.4127F, 5.375F, 0.3054F, 0.1745F, 0.0F));

		PartDefinition bone8 = root.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(27, 2).mirror().addBox(-3.0F, -3.0F, 0.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.75F, -5.45F, -2.35F, 0.3491F, -0.3927F, 0.0F));
		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(35, 18).mirror().addBox(-3.5F, -8.9127F, 2.625F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 6.25F, 4.0F, 0.5236F, 0.0F, 0.0F));

		bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(42, 18).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -8.4127F, 5.375F, 0.3054F, -0.1745F, 0.0F));
		root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 30).addBox(0.0F, 0.0F, -4.25F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -2.75F, -0.5F, 0.0F, 0.0F, -0.3229F));
		root.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(36, 23).addBox(3.0F, -6.25F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(42, 25).addBox(3.5F, -6.5F, 1.25F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.5F, -0.3957F, -0.1209F, 0.0503F));
		root.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(0, 30).mirror().addBox(-3.0F, 0.0F, -4.25F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, -2.75F, -0.5F, 0.0F, 0.0F, 0.3229F));
		root.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(42, 25).mirror().addBox(-4.5F, -6.5F, 1.25F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.5F, -0.3957F, 0.1209F, -0.0503F));
		root.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(36, 23).mirror().addBox(-4.0F, -6.25F, 0.0F, 1.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.5F, -0.3957F, 0.1209F, -0.0503F));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}
}