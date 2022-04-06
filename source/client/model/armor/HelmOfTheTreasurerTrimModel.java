package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.fullbright.FullbrightCubes;

import java.util.function.Function;

public class HelmOfTheTreasurerTrimModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_treasurer_trim"), "main");

	public HelmOfTheTreasurerTrimModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheTreasurerTrimModel> modelFactory() {
		return modelSet -> new HelmOfTheTreasurerTrimModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(16, 3).addBox(-1.5F, -8.75F, -5.5F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 10).addBox(1.5F, -8.25F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 10).addBox(-3.5F, -8.25F, -4.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(16, 0).addBox(-3.5F, -8.25F, 3.5F, 7.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 0).addBox(1.083F, -6.2641F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 3).addBox(-3.707F, -4.2588F, 3.48F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 3).mirror().addBox(2.707F, -4.2588F, 3.48F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(-2.1172F, -6.25F, 3.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-1.5F, -7.0F, 4.25F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(12, 14).addBox(3.25F, -8.25F, -3.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
				.texOffs(12, 14).mirror().addBox(-4.25F, -8.25F, -3.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(12, 14).addBox(3.25F, -8.25F, 0.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F))
				.texOffs(12, 14).mirror().addBox(-4.25F, -8.25F, 0.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(-0.01F)).mirror(false)
				.texOffs(22, 8).addBox(2.75F, -8.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(12, 7).addBox(4.15F, -6.25F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(12, 7).mirror().addBox(-4.15F, -6.25F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(22, 8).mirror().addBox(-4.75F, -8.25F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(-2.0F, -9.5F, -4.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		root.addOrReplaceChild("GEM", FullbrightCubes.create().texOffs(20, 14).mirror().addGlowBox(-4.5F, -7.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(20, 14).addGlowBox(4.5F, -7.75F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 13).addGlowBox(-0.5F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));

		PartDefinition bone5 = root.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(4, 11).addBox(-0.5F, -10.25F, -5.25F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -3.75F, -0.4363F, 0.0F, 0.0F));

		bone5.addOrReplaceChild("rcube_r1", CubeListBuilder.create().texOffs(24, 3).addBox(0.0F, -8.9F, -8.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(0, 13).addBox(-0.7587F, -4.8658F, 3.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 6).addBox(-2.7445F, -2.8517F, 3.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(0, 6).addBox(-5.2892F, -3.5822F, 3.46F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
				.texOffs(4, 0).mirror().addBox(-1.4608F, -7.3322F, 3.46F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 2.0F, 0.0F, 0.0F, 0.0F, 0.7854F));
		root.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(28, 4).addBox(4.65F, -5.25F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(28, 4).mirror().addBox(-3.65F, -5.25F, -1.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(18, 10).addBox(4.65F, -3.25F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(18, 10).mirror().addBox(-3.65F, -3.25F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.5F, -1.25F, -0.7854F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(24, 3).addBox(3.75F, -9.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.85F, 1.25F, 0.0F, 0.0F, 0.0F, -0.3491F));
		root.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(24, 3).mirror().addBox(-4.75F, -9.25F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.85F, 1.25F, 0.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition bone2 = root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(22, 8).addBox(0.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

		bone2.addOrReplaceChild("rcube_r2", CubeListBuilder.create().texOffs(24, 3).addBox(0.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		bone2.addOrReplaceChild("GEM2", CubeListBuilder.create().texOffs(20, 14).addBox(0.5F, -7.75F, -6.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		bone2.addOrReplaceChild("bone13", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone8 = root.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(22, 8).mirror().addBox(-2.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

		bone8.addOrReplaceChild("rcube_r3", CubeListBuilder.create().texOffs(24, 3).mirror().addBox(-1.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		bone8.addOrReplaceChild("GEM3", FullbrightCubes.create().texOffs(19, 15).addGlowBox(-0.5F, -7.75F, -6.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition bone7 = root.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(22, 8).addBox(0.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 13).addBox(0.5305F, -6.26F, -5.9659F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -0.75F, 0.0F, -2.3562F, 0.0F));

		bone7.addOrReplaceChild("rcube_r4", CubeListBuilder.create().texOffs(24, 3).addBox(0.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		bone7.addOrReplaceChild("GEM4", FullbrightCubes.create().texOffs(20, 14).addGlowBox(1.5F, -7.75F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.75F));

		PartDefinition bone10 = root.addOrReplaceChild("bone10", CubeListBuilder.create().texOffs(22, 8).mirror().addBox(-2.0F, -8.25F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 13).mirror().addBox(-1.5305F, -6.26F, -5.9659F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.75F, 0.0F, 2.3562F, 0.0F));

		bone10.addOrReplaceChild("rcube_r5", CubeListBuilder.create().texOffs(24, 3).mirror().addBox(-1.5F, -6.75F, -8.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));
		bone10.addOrReplaceChild("GEM5", FullbrightCubes.create().texOffs(20, 14).addGlowBox(-1.5F, -7.75F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.75F));
		root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 16).addBox(-3.7999F, -9.0588F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6545F));
		root.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(1.7999F, -9.0588F, -4.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6545F));

		return LayerDefinition.create(meshDefinition, 32, 32);
	}
}