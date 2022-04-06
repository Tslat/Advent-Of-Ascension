package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;

import java.util.function.Function;

public class HelmOfTheDextrousTrimModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_dextrous_trim"), "main");

	public HelmOfTheDextrousTrimModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheDextrousTrimModel> modelFactory() {
		return modelSet -> new HelmOfTheDextrousTrimModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", FullbrightCubes.create().texOffs(32, 0).addBox(-0.4006F, -10.5487F, -4.251F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13).mirror().addBox(-3.5F, -10.0487F, 1.65F, 7.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(18, 16).addBox(0.5F, -11.0487F, -2.25F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(25, 8).mirror().addBox(-0.5F, -11.0487F, -2.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(25, 10).addBox(-0.5F, -4.0487F, 4.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(18, 16).mirror().addBox(-1.5F, -11.0487F, -2.25F, 1.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 19).addGlowBox(0.0F, -14.0487F, -1.25F, 0.0F, 10.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(32, 0).mirror().addBox(-0.5994F, -10.5487F, -4.251F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(28, 16).addBox(2.2423F, 0.6853F, 3.6478F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(22, 13).addBox(-0.4778F, -3.087F, 9.001F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 19).addBox(2.7423F, 2.6853F, 2.5478F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(35, 10).addBox(1.61F, -1.1005F, 0.499F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(50, 20).addBox(2.6079F, 0.1495F, 0.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -7.0F, -5.0F));

		PartDefinition bone2 = bone4.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(26, 0).mirror().addBox(-1.1633F, -1.0821F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(29, 2).addBox(-1.1633F, -2.0821F, 0.001F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 9).addBox(1.5867F, -0.8321F, 0.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(25, 2).addBox(0.5867F, -0.0821F, 0.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(33, 12).mirror().addBox(0.5867F, 3.6944F, 3.1478F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(21, 6).addBox(2.8367F, -1.0821F, 1.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(32, 11).addBox(0.59F, 2.7197F, 1.2555F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
		bone4.addOrReplaceChild("bone5", FullbrightCubes.create().texOffs(37, 20).addGlowBox(-4.3959F, -7.3154F, 0.8541F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.8464F, 6.3178F, 8.2956F, 0.8456F, 0.266F, 0.0577F));
		bone4.addOrReplaceChild("bone6", FullbrightCubes.create().texOffs(38, 24).addGlowBox(-4.6959F, -7.3998F, 1.3533F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.5964F, 8.0678F, 7.0456F, 0.5838F, 0.266F, 0.0577F));
		bone4.addOrReplaceChild("bone7", FullbrightCubes.create().texOffs(40, 29).addGlowBox(-4.8459F, -7.3774F, 1.3597F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0964F, 9.0678F, 5.0456F, 0.1911F, 0.266F, 0.0577F));
		bone4.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(46, 0).addBox(1.6777F, -1.9178F, 0.75F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, 0.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition bone9 = root.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(-4.2423F, 0.6853F, 3.6478F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 13).mirror().addBox(-2.5222F, -3.087F, 9.001F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(8, 19).mirror().addBox(-3.7423F, 2.6853F, 2.5478F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(35, 10).mirror().addBox(-3.61F, -1.1005F, 0.499F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(50, 20).mirror().addBox(-3.6079F, 0.1495F, 0.5F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -7.0F, -5.0F));

		PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(26, 0).addBox(-3.8367F, -1.0821F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(29, 2).addBox(-0.8367F, -2.0821F, 0.001F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(42, 9).mirror().addBox(-3.5867F, -0.8321F, 0.5F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(25, 2).mirror().addBox(-1.5867F, -0.0821F, 0.25F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(33, 12).addBox(-1.5867F, 3.6944F, 3.1478F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(21, 6).mirror().addBox(-3.8367F, -1.0821F, 1.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		bone10.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(32, 11).mirror().addBox(-1.59F, 2.7197F, 1.2555F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
		bone9.addOrReplaceChild("bone12", FullbrightCubes.create().texOffs(37, 20).mirror().addGlowBox(4.3959F, -7.3154F, 0.8541F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.8464F, 6.3178F, 8.2956F, 0.8456F, -0.266F, -0.0577F));
		bone9.addOrReplaceChild("bone13", FullbrightCubes.create().texOffs(38, 24).mirror().addGlowBox(4.6959F, -7.3998F, 1.3533F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.5964F, 8.0678F, 7.0456F, 0.5838F, -0.266F, -0.0577F));
		bone9.addOrReplaceChild("bone14", FullbrightCubes.create().texOffs(40, 29).mirror().addGlowBox(4.8459F, -7.3774F, 1.3597F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0964F, 9.0678F, 5.0456F, 0.1911F, -0.266F, -0.0577F));
		bone9.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(46, 0).mirror().addBox(-3.6777F, -1.9178F, 0.75F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 0.5F, 0.0F, 0.0F, 0.0F, 1.309F));
		root.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-8.25F, -1.25F, 9.65F, 9.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.75F, -4.75F, -7.75F, 0.1745F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone17", FullbrightCubes.create().texOffs(56, 28).addGlowBox(-6.5255F, -6.5255F, -5.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.35F)), PartPose.offsetAndRotation(0.0F, 0.2F, 0.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}
}