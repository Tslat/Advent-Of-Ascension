package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;

import java.util.function.Function;

public class HelmOfTheWarriorTrimModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_warrior_trim"), "main");

	public HelmOfTheWarriorTrimModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheWarriorTrimModel> modelFactory() {
		return modelSet -> new HelmOfTheWarriorTrimModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -9.25F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(40, 16).addBox(4.5F, -7.75F, -0.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(40, 16).mirror().addBox(-5.5F, -7.75F, -0.5F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(47, 18).addBox(2.499F, -7.749F, 4.191F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(47, 18).mirror().addBox(-5.499F, -7.749F, 4.191F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(58, 21).addBox(0.8468F, -7.8343F, 4.192F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(58, 21).mirror().addBox(-2.8101F, -7.8343F, 4.192F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(27, 0).addBox(-4.499F, -7.9F, 3.75F, 9.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(1, 12).addBox(3.65F, -7.01F, -4.5F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(1, 12).mirror().addBox(-4.75F, -7.01F, -4.5F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 24.0F, 0.0F));

		root.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(47, 1).addBox(-2.9895F, -11.8838F, -6.1947F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.511F, -1.25F, 3.4886F, 0.2182F, -0.2182F, 0.7854F));
		root.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(47, 1).addBox(-2.1095F, -11.3435F, -4.2969F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.511F, -1.15F, 3.4886F, 0.0F, 0.0F, 0.7854F));
		root.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(55, 16).addBox(-2.1095F, -11.3435F, -0.2976F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(52, 21).addBox(-0.1095F, -11.3435F, -0.2976F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.511F, -1.15F, 4.4886F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(18, 12).addBox(-6.511F, -7.0F, -6.5114F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, 2.25F, 0.0F, -0.7854F, 0.0F));

		bone4.addOrReplaceChild("GEM", FullbrightCubes.create().texOffs(16, 12).addGlowBox(-0.25F, -6.5F, -5.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.511F, 0.0F, -1.0114F));
		bone4.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 5).addBox(-1.0564F, -7.3852F, -1.0071F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(12, 14).addBox(-0.8239F, -2.5308F, -0.8252F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.659F, -1.4918F, -5.7033F, -0.0873F, 0.0F, 0.0873F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 12).addBox(-0.818F, -9.0F, -5.5114F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(-0.818F, -4.0F, -5.5114F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(2.182F, -6.0F, -5.5114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition bone3 = bone2.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(26, 18).addBox(-0.5F, -1.251F, -5.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
				.texOffs(26, 12).addBox(0.0F, -7.01F, -5.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F))
				.texOffs(27, 16).addBox(-0.5368F, -4.279F, -4.8876F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.818F, 0.0F, -1.0114F, 0.0F, 0.0F, -0.0873F));

		PartDefinition bone8 = bone3.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(47, 9).addBox(5.5237F, -7.25F, -2.7507F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(20, 17).addBox(5.068F, -1.5F, -2.962F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(46, 11).addBox(5.1106F, -4.278F, -2.5435F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(40, 8).addBox(4.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 8).addBox(4.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0706F, 5.5F, 2.538F, -0.6109F, 0.0F, 0.0F));

		bone9.addOrReplaceChild("GLOW_1", FullbrightCubes.create().texOffs(58, 8).addGlowBox(3.9943F, -10.25F, -6.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.4532F, -3.6403F, -2.9024F));
		bone9.addOrReplaceChild("GLOW_2", FullbrightCubes.create().texOffs(58, 8).addGlowBox(3.9943F, -9.0F, -7.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.3532F, -2.6403F, -4.4024F, -0.5236F, 0.0F, 0.0F));

		PartDefinition bone6 = root.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-3.182F, -9.0F, -5.5114F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 16).mirror().addBox(-3.182F, -4.0F, -5.5114F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-3.182F, -6.0F, -5.5114F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(26, 18).mirror().addBox(-4.5F, -1.251F, -5.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false)
				.texOffs(26, 12).mirror().addBox(-5.0F, -7.01F, -5.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.25F)).mirror(false)
				.texOffs(27, 16).mirror().addBox(-4.4632F, -4.279F, -4.8876F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.818F, 0.0F, -1.0114F, 0.0F, 0.0F, 0.0873F));

		PartDefinition bone13 = bone7.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(47, 9).mirror().addBox(-6.5237F, -7.25F, -2.7507F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(20, 17).mirror().addBox(-6.068F, -1.5F, -2.962F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(46, 11).mirror().addBox(-6.1106F, -4.278F, -2.5435F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.4363F, 0.0F));

		PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(40, 8).mirror().addBox(-5.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(40, 8).mirror().addBox(-5.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0706F, 5.5F, 2.538F, -0.6109F, 0.0F, 0.0F));

		bone14.addOrReplaceChild("GLOW_4", FullbrightCubes.create().texOffs(58, 8).mirror().addGlowBox(-4.9943F, -10.25F, -6.5F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.4532F, -3.6403F, -2.9024F));
		bone14.addOrReplaceChild("GLOW_3", FullbrightCubes.create().texOffs(58, 8).mirror().addGlowBox(-4.9943F, -9.0F, -7.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.3532F, -2.6403F, -4.4024F, -0.5236F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}
}