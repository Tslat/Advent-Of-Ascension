package net.tslat.aoa3.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.advent.AdventOfAscension;

public class HelmOfTheWarriorModel extends SkillHelmetModel {
	public HelmOfTheWarriorModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_warrior.png"));
		texWidth = 64;
		texHeight = 32;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.5F, -9.25F, -4.5F, 9.0F, 3.0F, 9.0F, 0.0F, false);
		head.texOffs(40, 16).addBox(4.5F, -7.75F, -0.5F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		head.texOffs(40, 16).addBox(-5.5F, -7.75F, -0.5F, 1.0F, 2.0F, 5.0F, 0.0F, true);
		head.texOffs(47, 18).addBox(2.499F, -7.749F, 4.191F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(47, 18).addBox(-5.499F, -7.749F, 4.191F, 3.0F, 2.0F, 1.0F, 0.0F, true);
		head.texOffs(58, 21).addBox(0.8468F, -7.8343F, 4.192F, 2.0F, 7.0F, 1.0F, 0.0F, false);
		head.texOffs(58, 21).addBox(-2.8101F, -7.8343F, 4.192F, 2.0F, 7.0F, 1.0F, 0.0F, true);
		head.texOffs(27, 0).addBox(-4.499F, -7.9F, 3.75F, 9.0F, 7.0F, 1.0F, 0.0F, false);
		head.texOffs(1, 12).addBox(3.65F, -7.01F, -4.5F, 1.0F, 7.0F, 9.0F, 0.0F, false);
		head.texOffs(1, 12).addBox(-4.75F, -7.01F, -4.5F, 1.0F, 7.0F, 9.0F, 0.0F, true);

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setPos(-6.511F, -1.25F, 3.4886F);
		head.addChild(bone10);
		setRotationAngle(bone10, 0.2182F, -0.2182F, 0.7854F);
		bone10.texOffs(47, 1).addBox(-2.9895F, -11.8838F, -6.1947F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		ModelRenderer bone11 = new ModelRenderer(this);
		bone11.setPos(-6.511F, -1.15F, 3.4886F);
		head.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.7854F);
		bone11.texOffs(47, 1).addBox(-2.1095F, -11.3435F, -4.2969F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		ModelRenderer bone12 = new ModelRenderer(this);
		bone12.setPos(-6.511F, -1.15F, 4.4886F);
		head.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.7854F);
		bone12.texOffs(55, 16).addBox(-2.1095F, -11.3435F, -0.2976F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		bone12.texOffs(52, 21).addBox(-0.1095F, -11.3435F, -0.2976F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -0.25F, 2.25F);
		head.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.7854F, 0.0F);
		bone4.texOffs(18, 12).addBox(-6.511F, -7.0F, -6.5114F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setPos(-5.659F, -1.4918F, -5.7033F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.0873F, 0.0F, 0.0873F);
		bone5.texOffs(0, 5).addBox(-1.0564F, -7.3852F, -1.0071F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		bone5.texOffs(12, 14).addBox(-0.8239F, -2.5308F, -0.8252F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.2618F, 0.0F);
		bone2.texOffs(0, 12).addBox(-0.818F, -9.0F, -5.5114F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone2.texOffs(0, 16).addBox(-0.818F, -4.0F, -5.5114F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		bone2.texOffs(0, 0).addBox(2.182F, -6.0F, -5.5114F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setPos(-0.818F, 0.0F, -1.0114F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.0873F);
		bone3.texOffs(26, 18).addBox(-0.5F, -1.251F, -5.5F, 5.0F, 2.0F, 2.0F, -0.25F, false);
		bone3.texOffs(26, 12).addBox(0.0F, -7.01F, -5.5F, 5.0F, 2.0F, 2.0F, -0.25F, false);
		bone3.texOffs(27, 16).addBox(-0.5368F, -4.279F, -4.8876F, 5.0F, 1.0F, 1.0F, 0.0F, false);

		ModelRenderer bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.4363F, 0.0F);
		bone8.texOffs(47, 9).addBox(5.5237F, -7.25F, -2.7507F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		bone8.texOffs(20, 17).addBox(5.068F, -1.5F, -2.962F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		bone8.texOffs(46, 11).addBox(5.1106F, -4.278F, -2.5435F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setPos(1.0706F, 5.5F, 2.538F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, -0.6109F, 0.0F, 0.0F);
		bone9.texOffs(40, 8).addBox(4.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, 0.0F, false);
		bone9.texOffs(40, 8).addBox(4.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, 0.0F, false);
		bone9.texOffs(54, 9).addBox(4.4475F, -11.6403F, -9.4024F, 1.0F, 3.0F, 2.0F, 0.0F, false);

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.2618F, 0.0F);
		bone6.texOffs(0, 12).addBox(-3.182F, -9.0F, -5.5114F, 4.0F, 3.0F, 1.0F, 0.0F, true);
		bone6.texOffs(0, 16).addBox(-3.182F, -4.0F, -5.5114F, 4.0F, 4.0F, 1.0F, 0.0F, true);
		bone6.texOffs(0, 0).addBox(-3.182F, -6.0F, -5.5114F, 1.0F, 2.0F, 1.0F, 0.0F, true);

		ModelRenderer bone7 = new ModelRenderer(this);
		bone7.setPos(0.818F, 0.0F, -1.0114F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.0873F);
		bone7.texOffs(26, 18).addBox(-4.5F, -1.251F, -5.5F, 5.0F, 2.0F, 2.0F, -0.25F, true);
		bone7.texOffs(26, 12).addBox(-5.0F, -7.01F, -5.5F, 5.0F, 2.0F, 2.0F, -0.25F, true);
		bone7.texOffs(27, 16).addBox(-4.4632F, -4.279F, -4.8876F, 5.0F, 1.0F, 1.0F, 0.0F, true);

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, 0.0F, 0.0F);
		bone7.addChild(bone13);
		setRotationAngle(bone13, 0.0F, -0.4363F, 0.0F);
		bone13.texOffs(47, 9).addBox(-6.5237F, -7.25F, -2.7507F, 1.0F, 2.0F, 5.0F, 0.0F, true);
		bone13.texOffs(20, 17).addBox(-6.068F, -1.5F, -2.962F, 1.0F, 2.0F, 2.0F, 0.0F, true);
		bone13.texOffs(46, 11).addBox(-6.1106F, -4.278F, -2.5435F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setPos(-1.0706F, 5.5F, 2.538F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, -0.6109F, 0.0F, 0.0F);
		bone14.texOffs(40, 8).addBox(-5.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, 0.0F, true);
		bone14.texOffs(40, 8).addBox(-5.0F, -8.5794F, -8.5204F, 1.0F, 6.0F, 2.0F, 0.0F, true);
		bone14.texOffs(54, 9).addBox(-5.4475F, -11.6403F, -9.4024F, 1.0F, 3.0F, 2.0F, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}