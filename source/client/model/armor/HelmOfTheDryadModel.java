package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.tslat.aoa3.advent.AdventOfAscension;

import java.util.function.Function;

public class HelmOfTheDryadModel extends SkillHelmetModel {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AdventOfAscension.id("helm_of_the_dryad"), "main");

	public HelmOfTheDryadModel(ModelPart meshDefRoot) {
		super(meshDefRoot);
	}

	public static Function<EntityModelSet, HelmOfTheDryadModel> modelFactory() {
		return modelSet -> new HelmOfTheDryadModel(modelSet.bakeLayer(LAYER_LOCATION));
	}

	public static LayerDefinition createLayerDefinition() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		PartDefinition root = partDefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(20, 0).addBox(-4.0F, -8.5F, -5.0F, 8.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(-4.5F, -8.4F, 2.1F, 9.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).mirror().addBox(-4.8F, -8.0F, -4.4F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(3.8F, -8.0F, -4.4F, 1.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(12, 25).addBox(-1.5F, -8.7071F, -5.7071F, 3.0F, 4.0F, 1.0F, new CubeDeformation(-0.25F))
				.texOffs(0, 27).mirror().addBox(1.25F, -7.2071F, -5.45F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 27).addBox(-3.25F, -7.2071F, -5.45F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition horn1 = root.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(38, 34).addBox(1.0F, -10.5F, -5.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-3.0F, -4.0F, -1.25F, -0.3054F, 0.0F, 0.6981F));

		PartDefinition bone22 = horn1.addOrReplaceChild("bone22", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5F, -1.75F, -0.4443F, 0.0873F, -0.4005F));

		bone22.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 42).addBox(2.0F, -11.5F, -2.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F))
				.texOffs(38, 42).addBox(1.75F, -8.5F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.6837F, -1.4382F, -3.0115F, 0.0F, 0.0F, 0.0F));

		PartDefinition bone23 = horn1.addOrReplaceChild("bone23", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.25F, -7.5F, 3.25F, -0.1128F, 0.4389F, 0.7285F));

		bone23.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 46).addBox(2.75F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(3.6837F, -1.4382F, -3.0115F, 0.0F, 0.0F, 0.0F));

		PartDefinition horn2 = root.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(38, 34).mirror().addBox(-4.0F, -10.5F, -5.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(-0.1F)).mirror(false), PartPose.offsetAndRotation(3.0F, -4.0F, -1.25F, -0.3054F, 0.0F, -0.6981F));

		PartDefinition bone21 = horn2.addOrReplaceChild("bone21", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.5F, -1.75F, -0.4443F, -0.0873F, 0.4005F));

		bone21.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 42).mirror().addBox(-3.5F, -11.5F, -2.75F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false)
				.texOffs(38, 42).mirror().addBox(-3.75F, -8.5F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.6837F, -1.4382F, -3.0115F, 0.0F, 0.0F, 0.0F));

		PartDefinition bone24 = horn2.addOrReplaceChild("bone24", CubeListBuilder.create(), PartPose.offsetAndRotation(5.25F, -7.5F, 3.25F, -0.1128F, -0.4389F, -0.7285F));

		bone24.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(46, 46).mirror().addBox(-3.75F, -8.5F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(-3.6837F, -1.4382F, -3.0115F, 0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 24).addBox(-0.1205F, -0.1697F, -5.8071F, 2.0F, 2.0F, 1.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(0.0F, -6.25F, 0.25F, 0.0F, 0.0F, 0.7854F));

		PartDefinition bone4 = root.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(20, 22).mirror().addBox(0.7961F, -3.2931F, -5.6071F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -6.25F, 0.25F, 0.0F, 0.0F, 1.0472F));

		PartDefinition bone6 = bone4.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(20, 25).addBox(-0.3332F, -7.74F, -7.4593F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7039F, 4.4569F, -0.5571F, 0.0F, -0.8727F, 0.0F));

		PartDefinition bone7 = bone6.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(20, 25).addBox(-2.9012F, 2.2444F, -0.8259F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3098F, -7.4312F, -6.6234F, 0.0F, 0.0F, 0.7418F));

		PartDefinition bone8 = bone7.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(24, 25).mirror().addBox(3.2821F, -8.0128F, -3.9714F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(24, 25).mirror().addBox(3.2821F, -7.2628F, -3.9614F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5054F, 10.239F, 4.55F, 0.0F, 0.6981F, 0.0F));

		PartDefinition bone9 = bone8.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(20, 27).addBox(2.25F, -10.6027F, 0.8676F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7821F, 1.7372F, 1.0786F, 0.5236F, 0.0F, 0.0F));

		PartDefinition bone10 = bone9.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.8057F, 5.3356F, 0.0F, -1.1345F, 0.0F));

		bone10.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(29, 27).mirror().addBox(-4.5F, -9.0F, 4.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.9776F, 0.953F, -0.2262F, 0.0F, 0.0F, 0.0F));
		bone10.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(27, 22).addBox(2.5F, -10.25F, -3.8F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0224F, 0.203F, -0.2262F, 0.0F, 0.0F, 0.0F));

		PartDefinition bone5 = root.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(20, 22).addBox(-4.7961F, -3.2931F, -5.6071F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.25F, 0.25F, 0.0F, 0.0F, -1.0472F));

		PartDefinition bone11 = bone5.addOrReplaceChild("bone11", CubeListBuilder.create().texOffs(20, 25).mirror().addBox(-0.6668F, -7.74F, -7.4593F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.7039F, 4.4569F, -0.5571F, 0.0F, 0.8727F, 0.0F));

		PartDefinition bone12 = bone11.addOrReplaceChild("bone12", CubeListBuilder.create().texOffs(20, 25).mirror().addBox(1.9012F, 2.2444F, -0.8259F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.3098F, -7.4312F, -6.6234F, 0.0F, 0.0F, -0.7418F));

		PartDefinition bone13 = bone12.addOrReplaceChild("bone13", CubeListBuilder.create().texOffs(24, 25).addBox(-6.2821F, -8.0128F, -3.9714F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 25).addBox(-6.2821F, -7.2628F, -3.9614F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5054F, 10.239F, 4.55F, 0.0F, -0.6981F, 0.0F));

		PartDefinition bone14 = bone13.addOrReplaceChild("bone14", CubeListBuilder.create().texOffs(20, 27).mirror().addBox(-4.25F, -10.6027F, 0.8676F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.7821F, 1.7372F, 1.0786F, 0.5236F, 0.0F, 0.0F));

		PartDefinition bone15 = bone14.addOrReplaceChild("bone15", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.8057F, 5.3356F, 0.0F, 1.1345F, 0.0F));

		bone15.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(29, 27).addBox(-4.5F, -8.25F, 4.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(27, 22).mirror().addBox(-4.5F, -10.25F, -3.8F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0224F, 0.203F, -0.2262F, 0.0F, 0.0F, 0.0F));
		root.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(37, 14).addBox(-3.5F, -5.0F, -2.25F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-1.5F, -5.5962F, -2.4749F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.25F, 0.25F, 0.7854F, 0.0F, 0.0F));

		PartDefinition bone16 = root.addOrReplaceChild("bone16", CubeListBuilder.create().texOffs(15, 13).addBox(-3.0F, -5.0F, -2.25F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(1, 43).addBox(-4.0F, 4.0F, -0.6F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-1.495F, -5.5962F, -2.4749F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(41, 26).addBox(-2.0F, -3.5962F, 1.7751F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.25F, 2.25F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bone17 = bone16.addOrReplaceChild("bone17", CubeListBuilder.create().texOffs(24, 34).addBox(-1.5F, -4.25F, -0.75F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9038F, 2.5251F, -0.3054F, 0.0F, 0.0F));

		bone17.addOrReplaceChild("bone18", CubeListBuilder.create().texOffs(25, 40).addBox(-0.5F, -3.25F, -1.75F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 1.25F, -0.7854F, 0.0F, 0.0F));
		bone17.addOrReplaceChild("bone19", CubeListBuilder.create().texOffs(23, 45).addBox(-1.5F, -3.25F, -1.75F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 1.0F, 2.5F, -0.3927F, 0.0F, 0.0F));
		bone17.addOrReplaceChild("bone20", CubeListBuilder.create().texOffs(26, 53).addBox(0.5F, -3.25F, -0.75F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 4.5F, 2.75F, 0.48F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}
}