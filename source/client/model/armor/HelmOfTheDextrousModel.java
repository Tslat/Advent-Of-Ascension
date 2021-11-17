package net.tslat.aoa3.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.advent.AdventOfAscension;

public class HelmOfTheDextrousModel extends SkillHelmetModel {
	public HelmOfTheDextrousModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_dextrous.png"));

		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(32, 0).addBox(-0.4006F, -10.5487F, -4.251F, 1.0F, 3.0F, 6.0F, 0.0F, false);
		head.texOffs(0, 13).addBox(-3.5F, -10.0487F, 1.65F, 7.0F, 3.0F, 3.0F, -0.1F, true);
		head.texOffs(18, 16).addBox(0.5F, -11.0487F, -2.25F, 1.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(25, 8).addBox(-0.5F, -11.0487F, -2.25F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		head.texOffs(25, 10).addBox(-0.5F, -4.0487F, 4.75F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(18, 16).addBox(-1.5F, -11.0487F, -2.25F, 1.0F, 8.0F, 8.0F, 0.0F, true);
		head.texOffs(0, 19).addBox(0.0F, -14.0487F, -1.25F, 0.0F, 10.0F, 9.0F, 0.0F, false);
		head.texOffs(32, 0).addBox(-0.5994F, -10.5487F, -4.251F, 1.0F, 3.0F, 6.0F, 0.0F, true);

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setPos(1.0F, -7.0F, -5.0F);
		head.addChild(bone4);
		bone4.texOffs(28, 16).addBox(2.2423F, 0.6853F, 3.6478F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		bone4.texOffs(22, 13).addBox(-0.4778F, -3.087F, 9.001F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		bone4.texOffs(8, 19).addBox(2.7423F, 2.6853F, 2.5478F, 1.0F, 5.0F, 4.0F, 0.0F, false);
		bone4.texOffs(35, 10).addBox(1.61F, -1.1005F, 0.499F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		bone4.texOffs(50, 20).addBox(2.6079F, 0.1495F, 0.5F, 1.0F, 2.0F, 6.0F, 0.0F, false);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.6109F);
		bone2.texOffs(26, 0).addBox(-1.1633F, -1.0821F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
		bone2.texOffs(29, 2).addBox(-1.1633F, -2.0821F, 0.001F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.texOffs(42, 9).addBox(1.5867F, -0.8321F, 0.5F, 2.0F, 2.0F, 9.0F, 0.0F, false);
		bone2.texOffs(25, 2).addBox(0.5867F, -0.0821F, 0.25F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(33, 12).addBox(0.5867F, 3.6944F, 3.1478F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		bone2.texOffs(21, 6).addBox(2.8367F, -1.0821F, 1.0F, 1.0F, 1.0F, 9.0F, 0.0F, false);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, -0.2618F, 0.0F, 0.0F);
		bone3.texOffs(32, 11).addBox(0.59F, 2.7197F, 1.2555F, 1.0F, 1.0F, 3.0F, 0.0F, false);

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setPos(8.8464F, 6.3178F, 8.2956F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.8456F, 0.266F, 0.0577F);
		bone5.texOffs(37, 20).addBox(-4.3959F, -7.3154F, 0.8541F, 0.0F, 3.0F, 9.0F, 0.0F, false);

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setPos(8.5964F, 8.0678F, 7.0456F);
		bone4.addChild(bone6);
		setRotationAngle(bone6, 0.5838F, 0.266F, 0.0577F);
		bone6.texOffs(38, 24).addBox(-4.6959F, -7.3998F, 1.3533F, 0.0F, 3.0F, 8.0F, 0.0F, false);

		ModelRenderer bone7 = new ModelRenderer(this);
		bone7.setPos(8.0964F, 9.0678F, 5.0456F);
		bone4.addChild(bone7);
		setRotationAngle(bone7, 0.1911F, 0.266F, 0.0577F);
		bone7.texOffs(40, 29).addBox(-4.8459F, -7.3774F, 1.3597F, 0.0F, 3.0F, 6.0F, 0.0F, false);

		ModelRenderer bone8 = new ModelRenderer(this);
		bone8.setPos(0.5F, 0.5F, 0.0F);
		bone4.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -1.309F);
		bone8.texOffs(46, 0).addBox(1.6777F, -1.9178F, 0.75F, 2.0F, 3.0F, 6.0F, 0.0F, false);

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setPos(-1.0F, -7.0F, -5.0F);
		head.addChild(bone9);
		bone9.texOffs(28, 16).addBox(-4.2423F, 0.6853F, 3.6478F, 2.0F, 3.0F, 3.0F, 0.0F, true);
		bone9.texOffs(22, 13).addBox(-2.5222F, -3.087F, 9.001F, 3.0F, 1.0F, 1.0F, 0.0F, true);
		bone9.texOffs(8, 19).addBox(-3.7423F, 2.6853F, 2.5478F, 1.0F, 5.0F, 4.0F, 0.0F, true);
		bone9.texOffs(35, 10).addBox(-3.61F, -1.1005F, 0.499F, 2.0F, 2.0F, 6.0F, 0.0F, true);
		bone9.texOffs(50, 20).addBox(-3.6079F, 0.1495F, 0.5F, 1.0F, 2.0F, 6.0F, 0.0F, true);

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 0.0F, 0.0F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.6109F);
		bone10.texOffs(26, 0).addBox(-3.8367F, -1.0821F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		bone10.texOffs(29, 2).addBox(-0.8367F, -2.0821F, 0.001F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone10.texOffs(42, 9).addBox(-3.5867F, -0.8321F, 0.5F, 2.0F, 2.0F, 9.0F, 0.0F, true);
		bone10.texOffs(25, 2).addBox(-1.5867F, -0.0821F, 0.25F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		bone10.texOffs(33, 12).addBox(-1.5867F, 3.6944F, 3.1478F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		bone10.texOffs(21, 6).addBox(-3.8367F, -1.0821F, 1.0F, 1.0F, 1.0F, 9.0F, 0.0F, true);

		ModelRenderer bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.0F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, -0.2618F, 0.0F, 0.0F);
		bone11.texOffs(32, 11).addBox(-1.59F, 2.7197F, 1.2555F, 1.0F, 1.0F, 3.0F, 0.0F, true);

		ModelRenderer bone12 = new ModelRenderer(this);
		bone12.setPos(-8.8464F, 6.3178F, 8.2956F);
		bone9.addChild(bone12);
		setRotationAngle(bone12, 0.8456F, -0.266F, -0.0577F);
		bone12.texOffs(39, 22).addBox(4.3959F, -7.3154F, 0.8541F, 0.0F, 2.0F, 7.0F, 0.0F, true);

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setPos(-8.5964F, 8.0678F, 7.0456F);
		bone9.addChild(bone13);
		setRotationAngle(bone13, 0.5838F, -0.266F, -0.0577F);
		bone13.texOffs(39, 25).addBox(4.6959F, -7.3998F, 1.3533F, 0.0F, 2.0F, 6.0F, 0.0F, true);

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setPos(-8.0964F, 9.0678F, 5.0456F);
		bone9.addChild(bone14);
		setRotationAngle(bone14, 0.1911F, -0.266F, -0.0577F);
		bone14.texOffs(41, 30).addBox(4.8459F, -7.3774F, 1.3597F, 0.0F, 2.0F, 5.0F, 0.0F, true);

		ModelRenderer bone15 = new ModelRenderer(this);
		bone15.setPos(-0.5F, 0.5F, 0.0F);
		bone9.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, 1.309F);
		bone15.texOffs(46, 0).addBox(-3.6777F, -1.9178F, 0.75F, 2.0F, 3.0F, 6.0F, 0.0F, true);

		ModelRenderer bone16 = new ModelRenderer(this);
		bone16.setPos(3.75F, -4.75F, -7.75F);
		head.addChild(bone16);
		setRotationAngle(bone16, 0.1745F, 0.0F, 0.0F);
		bone16.texOffs(0, 0).addBox(-8.25F, -1.25F, 9.65F, 9.0F, 9.0F, 3.0F, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}