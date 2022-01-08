package net.tslat.aoa3.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.misc.FullbrightModelRenderer;

public class HelmOfTheTrawlerTrimModel extends SkillHelmetModel {
	public HelmOfTheTrawlerTrimModel() {
		super(AdventOfAscension.id("textures/models/armor/custom/helm_of_the_trawler_trim.png"));
		texWidth = 64;
		texHeight = 32;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(0, 0).addBox(-4.5F, -9.5F, -4.5F, 9.0F, 3.0F, 9.0F, 0.0F, false);
		head.texOffs(0, 22).addBox(-4.5F, -6.5F, 1.5F, 9.0F, 3.0F, 3.0F, 0.0F, false);
		head.texOffs(36, 0).addBox(-2.5F, -10.5F, -3.5F, 5.0F, 3.0F, 9.0F, 0.0F, false);
		head.texOffs(0, 12).addBox(-1.5F, -4.9517F, 4.0697F, 3.0F, 3.0F, 3.0F, -0.1F, false);

		ModelRenderer right_ear = new ModelRenderer(this);
		right_ear.setPos(0.5F, 3.2983F, 1.0F);
		head.addChild(right_ear);
		setRotationAngle(right_ear, 0.0873F, 0.0F, 0.0F);
		right_ear.texOffs(47, 16).addBox(3.6F, -9.9483F, 0.25F, 1.0F, 6.0F, 2.0F, 0.0F, false);
		right_ear.texOffs(40, 17).addBox(3.6F, -6.4483F, 0.25F, 1.0F, 1.0F, 1.0F, 0.1F, false);
		right_ear.texOffs(43, 16).addBox(3.6F, -6.4483F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -3.5483F, 0.0F);
		right_ear.addChild(bone13);
		setRotationAngle(bone13, 0.1309F, 0.0F, 0.0F);
		bone13.texOffs(43, 18).addBox(3.6F, -5.2024F, 2.0263F, 1.0F, 5.0F, 1.0F, -0.1F, false);
		bone13.texOffs(43, 16).addBox(3.6F, -4.15F, 0.25F, 1.0F, 1.0F, 1.0F, -0.1F, false);

		ModelRenderer bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, -2.0483F, 1.0F);
		right_ear.addChild(bone12);
		setRotationAngle(bone12, 0.2618F, 0.0F, 0.0F);
		bone12.texOffs(40, 17).addBox(3.6F, -2.9F, -0.1F, 1.0F, 1.0F, 1.0F, 0.2F, false);
		bone12.texOffs(49, 13).addBox(3.6F, -2.9F, -1.5F, 1.0F, 1.0F, 2.0F, 0.0F, false);

		ModelRenderer left_ear = new ModelRenderer(this);
		left_ear.setPos(-0.5F, 3.2983F, 1.0F);
		head.addChild(left_ear);
		setRotationAngle(left_ear, 0.0873F, 0.0F, 0.0F);
		left_ear.texOffs(47, 16).addBox(-4.6F, -9.9483F, 0.25F, 1.0F, 6.0F, 2.0F, 0.0F, true);
		left_ear.texOffs(40, 17).addBox(-4.6F, -6.4483F, 0.25F, 1.0F, 1.0F, 1.0F, 0.1F, true);
		left_ear.texOffs(43, 16).addBox(-4.6F, -6.4483F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, true);

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -3.5483F, 0.0F);
		left_ear.addChild(bone14);
		setRotationAngle(bone14, 0.1309F, 0.0F, 0.0F);
		bone14.texOffs(43, 18).addBox(-4.6F, -5.2024F, 2.0263F, 1.0F, 5.0F, 1.0F, -0.1F, true);
		bone14.texOffs(43, 16).addBox(-4.6F, -4.15F, 0.25F, 1.0F, 1.0F, 1.0F, -0.1F, true);

		ModelRenderer bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, -2.0483F, 1.0F);
		left_ear.addChild(bone15);
		setRotationAngle(bone15, 0.2618F, 0.0F, 0.0F);
		bone15.texOffs(40, 17).addBox(-4.6F, -2.9F, -0.1F, 1.0F, 1.0F, 1.0F, 0.2F, true);
		bone15.texOffs(49, 13).addBox(-4.6F, -2.9F, -1.5F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		ModelRenderer bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(bone7);
		setRotationAngle(bone7, -1.1345F, 0.0F, 0.0F);
		bone7.texOffs(27, 0).addBox(-2.0F, -8.5F, -5.5F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		bone7.texOffs(12, 12).addBox(-0.9F, -7.1838F, -0.164F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		ModelRenderer tail_end = new ModelRenderer(this);
		tail_end.setPos(-0.4F, 1.3162F, 6.586F);
		bone7.addChild(tail_end);
		tail_end.texOffs(16, 12).addBox(-3.6F, -7.5F, -3.85F, 8.0F, 0.0F, 3.0F, 0.0F, false);

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 0.5F, 0.0F);
		tail_end.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.6545F, 0.0F);
		bone10.texOffs(27, 7).addBox(-2.5F, -8.5F, -3.75F, 5.0F, 1.0F, 1.0F, 0.1F, false);

		ModelRenderer bone11 = new ModelRenderer(this);
		bone11.setPos(0.8F, 0.5F, 0.0F);
		tail_end.addChild(bone11);
		setRotationAngle(bone11, 0.0F, -0.6545F, 0.0F);
		bone11.texOffs(27, 7).addBox(-2.5F, -8.5F, -3.75F, 5.0F, 1.0F, 1.0F, 0.1F, true);

		ModelRenderer lantern = new ModelRenderer(this);
		lantern.setPos(0.0F, -12.75F, -5.0601F);
		head.addChild(lantern);
		setRotationAngle(lantern, 0.48F, 0.0F, 0.0F);
		lantern.texOffs(55, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, true);

		ModelRenderer bone16 = new ModelRenderer(this);
		bone16.setPos(-2.35F, 9.5F, 4.25F);
		lantern.addChild(bone16);
		setRotationAngle(bone16, 0.9163F, 0.0F, 0.0F);
		bone16.texOffs(55, 2).addBox(1.851F, -12.0627F, 4.6507F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		ModelRenderer bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, 0.0F, 0.0F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, 0.9163F, 0.0F, 0.0F);
		bone17.texOffs(55, 2).addBox(1.85F, -5.8603F, 12.0099F, 1.0F, 3.0F, 1.0F, 0.0F, true);

		ModelRenderer bulb = new FullbrightModelRenderer(this);
		bulb.setPos(0.0F, 0.0F, 0.0F);
		bone17.addChild(bulb);
		setRotationAngle(bulb, 0.9163F, 0.0F, 0.0F);
		bulb.texOffs(24, 23).addBox(1.35F, 3.7539F, 10.5692F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		ModelRenderer top_fins = new ModelRenderer(this);
		top_fins.setPos(0.0F, -3.75F, -4.0F);
		head.addChild(top_fins);
		setRotationAngle(top_fins, -0.5672F, 0.0F, 0.0F);
		top_fins.texOffs(55, 0).addBox(-0.5F, -11.5F, -1.5601F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		top_fins.texOffs(55, 10).addBox(0.0F, -11.5F, -0.5601F, 0.0F, 5.0F, 3.0F, 0.0F, false);
		top_fins.texOffs(53, 13).addBox(0.0F, -11.5F, 3.4399F, 0.0F, 3.0F, 5.0F, 0.0F, false);
		top_fins.texOffs(55, 0).addBox(-0.5F, -12.25F, 2.4399F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		top_fins.texOffs(54, 4).addBox(-0.5F, -9.25F, 4.6899F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		top_fins.texOffs(54, 17).addBox(0.0F, -8.25F, 5.6899F, 0.0F, 3.0F, 4.0F, 0.0F, false);

		ModelRenderer right_fins = new ModelRenderer(this);
		right_fins.setPos(2.3249F, -6.5F, -3.4479F);
		head.addChild(right_fins);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		right_fins.addChild(bone3);
		setRotationAngle(bone3, 0.0555F, 0.5648F, 0.1034F);
		bone3.texOffs(0, 18).addBox(0.2614F, -0.5F, -1.1122F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(14, 15).addBox(0.0114F, -0.25F, -0.1122F, 5.0F, 0.0F, 8.0F, 0.0F, false);

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -0.5F, 3.0F);
		right_fins.addChild(bone4);
		setRotationAngle(bone4, 0.0507F, -0.394F, 0.0542F);
		bone4.texOffs(0, 20).addBox(1.2614F, -0.5F, -1.1122F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		bone4.texOffs(26, 17).addBox(2.2614F, 0.0F, -0.1122F, 4.0F, 0.0F, 6.0F, 0.0F, false);

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setPos(-1.0F, -0.5F, 6.0F);
		right_fins.addChild(bone5);
		setRotationAngle(bone5, 0.1369F, -1.2204F, -0.055F);
		bone5.texOffs(12, 18).addBox(1.2614F, -0.5F, -1.1122F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		ModelRenderer left_fins = new ModelRenderer(this);
		left_fins.setPos(-2.3249F, -6.5F, -3.4479F);
		head.addChild(left_fins);

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		left_fins.addChild(bone6);
		setRotationAngle(bone6, 0.0555F, -0.5648F, -0.1034F);
		bone6.texOffs(0, 18).addBox(-5.2614F, -0.5F, -1.1122F, 5.0F, 1.0F, 1.0F, 0.0F, true);
		bone6.texOffs(14, 15).addBox(-5.0114F, -0.25F, -0.1122F, 5.0F, 0.0F, 8.0F, 0.0F, true);

		ModelRenderer bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -0.5F, 3.0F);
		left_fins.addChild(bone8);
		setRotationAngle(bone8, 0.0507F, 0.394F, -0.0542F);
		bone8.texOffs(0, 20).addBox(-7.2614F, -0.5F, -1.1122F, 6.0F, 1.0F, 1.0F, 0.0F, true);
		bone8.texOffs(26, 17).addBox(-6.2614F, 0.0F, -0.1122F, 4.0F, 0.0F, 6.0F, 0.0F, true);

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setPos(1.0F, -0.5F, 6.0F);
		left_fins.addChild(bone9);
		setRotationAngle(bone9, 0.1369F, 1.2204F, 0.055F);
		bone9.texOffs(12, 18).addBox(-5.2614F, -0.5F, -1.1122F, 4.0F, 1.0F, 1.0F, 0.0F, true);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setPos(-5.0F, -3.0F, 0.0F);
		head.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.texOffs(43, 13).addBox(-0.9497F, -8.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(38, 12).addBox(1.0503F, -8.0F, -5.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.texOffs(39, 14).addBox(-0.9497F, -6.0F, -5.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		ModelRenderer bone18 = new FullbrightModelRenderer(this);
		bone18.setPos(0.0503F, -7.0F, -4.75F);
		bone2.addChild(bone18);
		bone18.texOffs(30, 23).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}