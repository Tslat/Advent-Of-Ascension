package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;

import java.util.function.Function;

public class HelmOfTheTrawlerTrimModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_trawler_trim"), "main");

	public HelmOfTheTrawlerTrimModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheTrawlerTrimModel> modelFactory() {
		return modelSet -> new HelmOfTheTrawlerTrimModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -9.5F, -4.5F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 22).addBox(-4.5F, -6.5F, 1.5F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).addBox(-2.5F, -10.5F, -3.5F, 5.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-1.5F, -4.9517F, 4.0697F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_ear = root.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(47, 16).addBox(3.6F, -9.9483F, 0.25F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(40, 17).addBox(3.6F, -6.4483F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F))
				.texOffs(43, 16).addBox(3.6F, -6.4483F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.2983F, 1.0F, 0.0873F, 0.0F, 0.0F));

		right_ear.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(43, 18).addBox(3.6F, -5.2024F, 2.0263F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.1F))
				.texOffs(43, 16).addBox(3.6F, -4.15F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(0.0F, -3.5483F, 0.0F, 0.1309F, 0.0F, 0.0F));

		right_ear.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(40, 17).addBox(3.6F, -2.9F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F))
				.texOffs(49, 13).addBox(3.6F, -2.9F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0483F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition left_ear = root.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(47, 16).mirror().addBox(-4.6F, -9.9483F, 0.25F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(40, 17).mirror().addBox(-4.6F, -6.4483F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false)
				.texOffs(43, 16).mirror().addBox(-4.6F, -6.4483F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 3.2983F, 1.0F, 0.0873F, 0.0F, 0.0F));

		left_ear.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(43, 18).mirror().addBox(-4.6F, -5.2024F, 2.0263F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false)
				.texOffs(43, 16).mirror().addBox(-4.6F, -4.15F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(0.0F, -3.5483F, 0.0F, 0.1309F, 0.0F, 0.0F));
		left_ear.addOrReplaceChild("bone15", CubeListBuilder.create().texOffs(40, 17).mirror().addBox(-4.6F, -2.9F, -0.1F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.2F)).mirror(false)
				.texOffs(49, 13).mirror().addBox(-4.6F, -2.9F, -1.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -2.0483F, 1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition bone7 = root.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(27, 0).addBox(-2.0F, -8.5F, -5.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(12, 12).addBox(-0.9F, -7.1838F, -0.164F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.1345F, 0.0F, 0.0F));

		PartDefinition tail_end = bone7.addOrReplaceChild("tail_end", FullbrightCubes.create().texOffs(16, 12).addGlowBox(-3.6F, -7.5F, -3.85F, 8.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.4F, 1.3162F, 6.586F));

		tail_end.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(27, 7).addBox(-2.5F, -8.5F, -3.75F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.5F, 0.0F, 0.0F, 0.6545F, 0.0F));
		tail_end.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(27, 7).mirror().addBox(-2.5F, -8.5F, -3.75F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(0.8F, 0.5F, 0.0F, 0.0F, -0.6545F, 0.0F));

		PartDefinition lantern = root.addOrReplaceChild("lantern", CubeListBuilder.create().texOffs(55, 0).mirror().addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -12.75F, -5.0601F, 0.48F, 0.0F, 0.0F));

		PartDefinition bone16 = lantern.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(55, 2).mirror().addBox(1.851F, -12.0627F, 4.6507F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.35F, 9.5F, 4.25F, 0.9163F, 0.0F, 0.0F));

		PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(55, 2).mirror().addBox(1.85F, -5.8603F, 12.0099F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9163F, 0.0F, 0.0F));

		bone17.addOrReplaceChild("bulb", FullbrightCubes.create().texOffs(24, 23).addGlowBox(1.35F, 3.7539F, 10.5692F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.9163F, 0.0F, 0.0F));
		root.addOrReplaceChild("top_fins", FullbrightCubes.create().texOffs(55, 0).addBox(-0.5F, -11.5F, -1.5601F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(55, 10).addGlowBox(0.0F, -11.5F, -0.5601F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(53, 13).addGlowBox(0.0F, -11.5F, 3.4399F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(55, 0).addBox(-0.5F, -12.25F, 2.4399F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(54, 4).addBox(-0.5F, -9.25F, 4.6899F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(54, 17).addGlowBox(0.0F, -8.25F, 5.6899F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.75F, -4.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition right_fins = root.addOrReplaceChild("right_fins", CubeListBuilder.create(), PartPose.offset(2.3249F, -6.5F, -3.4479F));

		right_fins.addOrReplaceChild("bone3", FullbrightCubes.create().texOffs(0, 18).addBox(0.2614F, -0.5F, -1.1122F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(14, 15).addGlowBox(0.0114F, -0.25F, -0.1122F, 5.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0555F, 0.5648F, 0.1034F));
		right_fins.addOrReplaceChild("bone4", FullbrightCubes.create().texOffs(0, 20).addBox(1.2614F, -0.5F, -1.1122F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 17).addGlowBox(2.2614F, 0.0F, -0.1122F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 3.0F, 0.0507F, -0.394F, 0.0542F));
		right_fins.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(12, 18).addBox(1.2614F, -0.5F, -1.1122F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -0.5F, 6.0F, 0.1369F, -1.2204F, -0.055F));

		PartDefinition left_fins = root.addOrReplaceChild("left_fins", CubeListBuilder.create(), PartPose.offset(-2.3249F, -6.5F, -3.4479F));

		left_fins.addOrReplaceChild("bone6", FullbrightCubes.create().texOffs(0, 18).mirror().addBox(-5.2614F, -0.5F, -1.1122F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(14, 15).mirror().addGlowBox(-5.0114F, -0.25F, -0.1122F, 5.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0555F, -0.5648F, -0.1034F));
		left_fins.addOrReplaceChild("bone8", FullbrightCubes.create().texOffs(0, 20).mirror().addBox(-7.2614F, -0.5F, -1.1122F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(26, 17).mirror().addGlowBox(-6.2614F, 0.0F, -0.1122F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.5F, 3.0F, 0.0507F, 0.394F, -0.0542F));
		left_fins.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(12, 18).mirror().addBox(-5.2614F, -0.5F, -1.1122F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, -0.5F, 6.0F, 0.1369F, 1.2204F, 0.055F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(43, 13).addBox(-0.9497F, -8.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(38, 12).addBox(1.0503F, -8.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(39, 14).addBox(-0.9497F, -6.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

		bone2.addOrReplaceChild("bone18", FullbrightCubes.create().texOffs(30, 23).addGlowBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0503F, -7.0F, -4.75F));

		return LayerDefinition.create(meshDefinition, 64, 32);
	}
}