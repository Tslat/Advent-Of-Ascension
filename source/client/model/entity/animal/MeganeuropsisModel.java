package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.animal.MeganeuropsisEntity;

public class MeganeuropsisModel extends EntityModel<MeganeuropsisEntity> {
	private final ModelRenderer root;
	private final ModelRenderer bone8;
	private final ModelRenderer bone11;
	private final ModelRenderer bone15;
	private final ModelRenderer bone19;
	private final ModelRenderer bone23;
	private final ModelRenderer bone27;
	private final ModelRenderer wing1;
	private final ModelRenderer wing2;
	private final ModelRenderer wing3;
	private final ModelRenderer wing4;

	public MeganeuropsisModel() {
		texWidth = 32;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 19.0F, 0.0F);
		root.texOffs(14, 7).addBox(-1.0F, -1.0F, -4.0F, 2, 2, 2, 0.1F, false);
		root.texOffs(15, 11).addBox(0.75F, -1.0F, -4.15F, 1, 2, 2, 0.0F, false);
		root.texOffs(15, 11).addBox(-1.75F, -1.0F, -4.15F, 1, 2, 2, 0.0F, true);
		root.texOffs(0, 7).addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.15F, false);
		root.texOffs(0, 7).addBox(-1.0F, -1.0F, 0.0F, 2, 2, 2, 0.1F, true);

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, -1.0F, -4.0F);
		setRotation(bone6, 0.3491F, 0.0F, 0.0F);
		root.addChild(bone6);
		bone6.texOffs(22, 7).addBox(-1.0F, -0.1282F, -0.0598F, 2, 1, 2, 0.0F, false);
		bone6.texOffs(22, 10).addBox(-1.0F, 1.4693F, -0.6412F, 2, 1, 2, 0.0F, false);

		ModelRenderer tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 3.0F);
		setRotation(tail, 0.0349F, 0.0F, 0.0F);
		root.addChild(tail);
		tail.texOffs(0, 7).addBox(-1.0F, -0.9915F, -1.0961F, 2, 2, 2, -0.05F, false);
		tail.texOffs(0, 7).addBox(-1.0F, -0.9833F, 0.6567F, 2, 2, 2, -0.15F, true);
		tail.texOffs(0, 7).addBox(-1.0F, -0.9697F, 2.3097F, 2, 2, 2, -0.2F, false);
		tail.texOffs(0, 7).addBox(-1.0F, -0.951F, 3.8628F, 2, 2, 2, -0.25F, true);
		tail.texOffs(0, 7).addBox(-1.0F, -0.927F, 5.316F, 2, 2, 2, -0.3F, false);
		tail.texOffs(0, 7).addBox(-1.0F, -0.8952F, 6.6195F, 2, 2, 2, -0.35F, true);
		tail.texOffs(0, 7).addBox(-1.0F, -0.8581F, 7.8231F, 2, 2, 2, -0.4F, false);
		tail.texOffs(0, 7).addBox(-1.0F, -0.8158F, 8.9268F, 2, 2, 2, -0.45F, true);

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.25F, 10.0F);
		setRotation(bone4, 0.0F, -0.4363F, 0.0F);
		tail.addChild(bone4);
		bone4.texOffs(8, 7).addBox(-0.6848F, -0.4992F, -0.0609F, 1, 1, 2, -0.25F, false);

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.25F, 10.0F);
		setRotation(bone5, 0.0F, 0.4363F, 0.0F);
		tail.addChild(bone5);
		bone5.texOffs(8, 7).addBox(-0.3152F, -0.4992F, -0.0609F, 1, 1, 2, -0.25F, false);

		ModelRenderer leg = new ModelRenderer(this);
		leg.setPos(1.15F, 1.5F, -0.55F);
		root.addChild(leg);
		leg.texOffs(8, 11).addBox(-0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setPos(0.15F, -1.1F, 0.5F);
		setRotation(bone3, 0.0F, 0.0F, -1.1345F);
		leg.addChild(bone3);
		bone3.texOffs(0, 15).addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false);

		ModelRenderer bone7 = new ModelRenderer(this);
		bone7.setPos(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone7, 0.0F, 0.0F, 1.7453F);
		bone3.addChild(bone7);
		bone7.texOffs(4, 15).addBox(-0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(1.4585F, -0.3393F, 0.0F);
		setRotation(bone8, 0.0F, 0.0F, 2.0944F);
		bone7.addChild(bone8);
		bone8.texOffs(0, 11).addBox(-0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false);

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setPos(2.3222F, 0.2903F, 0.0F);
		setRotation(bone9, 0.0F, 0.0F, 2.0944F);
		bone8.addChild(bone9);
		bone9.texOffs(0, 13).addBox(-1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false);

		ModelRenderer leg2 = new ModelRenderer(this);
		leg2.setPos(0.6F, 1.6F, -1.3F);
		setRotation(leg2, 0.0F, 0.6981F, 0.0F);
		root.addChild(leg2);
		leg2.texOffs(8, 11).addBox(-0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setPos(0.15F, -1.1F, 0.5F);
		setRotation(bone2, 0.0F, 0.0F, -1.1345F);
		leg2.addChild(bone2);
		bone2.texOffs(0, 15).addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false);

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setPos(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone10, 0.0F, 0.0F, 1.7453F);
		bone2.addChild(bone10);
		bone10.texOffs(4, 15).addBox(-0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(1.4585F, -0.3393F, 0.0F);
		setRotation(bone11, 0.0F, 0.0F, 2.0944F);
		bone10.addChild(bone11);
		bone11.texOffs(0, 11).addBox(-0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false);

		ModelRenderer bone12 = new ModelRenderer(this);
		bone12.setPos(2.3222F, 0.2903F, 0.0F);
		setRotation(bone12, 0.0F, 0.0F, 2.0944F);
		bone11.addChild(bone12);
		bone12.texOffs(0, 13).addBox(-1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false);

		ModelRenderer leg3 = new ModelRenderer(this);
		leg3.setPos(1.2F, 1.6F, 0.4F);
		setRotation(leg3, 0.0F, -0.6981F, 0.0F);
		root.addChild(leg3);
		leg3.texOffs(8, 11).addBox(-0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setPos(0.15F, -1.1F, 0.5F);
		setRotation(bone13, 0.0F, 0.0F, -1.1345F);
		leg3.addChild(bone13);
		bone13.texOffs(0, 15).addBox(-1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false);

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setPos(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone14, 0.0F, 0.0F, 1.7453F);
		bone13.addChild(bone14);
		bone14.texOffs(4, 15).addBox(-0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(1.4585F, -0.3393F, 0.0F);
		setRotation(bone15, 0.0F, 0.0F, 2.0944F);
		bone14.addChild(bone15);
		bone15.texOffs(0, 11).addBox(-0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false);

		ModelRenderer bone16 = new ModelRenderer(this);
		bone16.setPos(2.3222F, 0.2903F, 0.0F);
		setRotation(bone16, 0.0F, 0.0F, 2.0944F);
		bone15.addChild(bone16);
		bone16.texOffs(0, 13).addBox(-1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false);

		ModelRenderer leg4 = new ModelRenderer(this);
		leg4.setPos(-1.3F, 1.5F, -0.55F);
		root.addChild(leg4);
		leg4.texOffs(8, 11).addBox(-0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true);

		ModelRenderer bone17 = new ModelRenderer(this);
		bone17.setPos(-0.15F, -1.1F, 0.5F);
		setRotation(bone17, 0.0F, 0.0F, 1.1345F);
		leg4.addChild(bone17);
		bone17.texOffs(0, 15).addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true);

		ModelRenderer bone18 = new ModelRenderer(this);
		bone18.setPos(0.7642F, 1.6963F, 0.0F);
		setRotation(bone18, 0.0F, 0.0F, -1.7453F);
		bone17.addChild(bone18);
		bone18.texOffs(4, 15).addBox(-1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true);

		bone19 = new ModelRenderer(this);
		bone19.setPos(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone19, 0.0F, 0.0F, -2.0944F);
		bone18.addChild(bone19);
		bone19.texOffs(0, 11).addBox(-2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true);

		ModelRenderer bone20 = new ModelRenderer(this);
		bone20.setPos(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone20, 0.0F, 0.0F, -2.0944F);
		bone19.addChild(bone20);
		bone20.texOffs(0, 13).addBox(-0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true);

		ModelRenderer leg5 = new ModelRenderer(this);
		leg5.setPos(-0.75F, 1.6F, -1.3F);
		setRotation(leg5, 0.0F, -0.6981F, 0.0F);
		root.addChild(leg5);
		leg5.texOffs(8, 11).addBox(-0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true);

		ModelRenderer bone21 = new ModelRenderer(this);
		bone21.setPos(-0.15F, -1.1F, 0.5F);
		setRotation(bone21, 0.0F, 0.0F, 1.1345F);
		leg5.addChild(bone21);
		bone21.texOffs(0, 15).addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true);

		ModelRenderer bone22 = new ModelRenderer(this);
		bone22.setPos(0.7642F, 1.6963F, 0.0F);
		setRotation(bone22, 0.0F, 0.0F, -1.7453F);
		bone21.addChild(bone22);
		bone22.texOffs(4, 15).addBox(-1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true);

		bone23 = new ModelRenderer(this);
		bone23.setPos(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone23, 0.0F, 0.0F, -2.0944F);
		bone22.addChild(bone23);
		bone23.texOffs(0, 11).addBox(-2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true);

		ModelRenderer bone24 = new ModelRenderer(this);
		bone24.setPos(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone24, 0.0F, 0.0F, -2.0944F);
		bone23.addChild(bone24);
		bone24.texOffs(0, 13).addBox(-0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true);

		ModelRenderer leg6 = new ModelRenderer(this);
		leg6.setPos(-1.35F, 1.6F, 0.4F);
		setRotation(leg6, 0.0F, 0.6981F, 0.0F);
		root.addChild(leg6);
		leg6.texOffs(8, 11).addBox(-0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true);

		ModelRenderer bone25 = new ModelRenderer(this);
		bone25.setPos(-0.15F, -1.1F, 0.5F);
		setRotation(bone25, 0.0F, 0.0F, 1.1345F);
		leg6.addChild(bone25);
		bone25.texOffs(0, 15).addBox(0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true);

		ModelRenderer bone26 = new ModelRenderer(this);
		bone26.setPos(0.7642F, 1.6963F, 0.0F);
		setRotation(bone26, 0.0F, 0.0F, -1.7453F);
		bone25.addChild(bone26);
		bone26.texOffs(4, 15).addBox(-1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true);

		bone27 = new ModelRenderer(this);
		bone27.setPos(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone27, 0.0F, 0.0F, -2.0944F);
		bone26.addChild(bone27);
		bone27.texOffs(0, 11).addBox(-2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true);

		ModelRenderer bone28 = new ModelRenderer(this);
		bone28.setPos(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone28, 0.0F, 0.0F, -2.0944F);
		bone27.addChild(bone28);
		bone28.texOffs(0, 13).addBox(-0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true);

		wing1 = new ModelRenderer(this);
		wing1.setPos(0.0F, -1.0F, 0.0F);
		setRotation(wing1, 0.0F, 0.1745F, 0.0873F);
		root.addChild(wing1);
		wing1.texOffs(0, 22).addBox(-12.25F, -0.1F, 0.25F, 12, 0, 4, 0.0F, false);

		wing2 = new ModelRenderer(this);
		wing2.setPos(0.0F, -1.0F, 0.0F);
		setRotation(wing2, 0.0F, -0.1745F, -0.0873F);
		root.addChild(wing2);
		wing2.texOffs(0, 22).addBox(0.25F, -0.1F, 0.25F, 12, 0, 4, 0.0F, true);

		wing3 = new ModelRenderer(this);
		wing3.setPos(0.0F, -1.25F, -1.25F);
		setRotation(wing3, 0.0F, -0.1745F, 0.0873F);
		root.addChild(wing3);
		wing3.texOffs(0, 18).addBox(-12.0472F, 0.1F, -0.75F, 12, 0, 4, 0.0F, false);

		wing4 = new ModelRenderer(this);
		wing4.setPos(0.0F, -1.25F, -1.25F);
		setRotation(wing4, 0.0F, 0.1745F, -0.0873F);
		root.addChild(wing4);
		wing4.texOffs(0, 18).addBox(0.0472F, 0.099F, -0.75F, 12, 0, 4, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MeganeuropsisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean landed = entity.isLanded();
		float modifier = landed ? 0.05f : 7.5f;
		float rot1 = -(MathHelper.sin(ageInTicks * modifier) * 0.4f) * 0.436332313f;
		float rot2 = -(MathHelper.sin(ageInTicks * modifier + ((float)Math.PI) / 2f) * 0.4f) * 0.436332313f;

		wing1.zRot = degToRad(-5) + rot1;
		wing2.zRot = degToRad(5) + -rot1;
		wing3.zRot = degToRad(-5) + rot2;
		wing4.zRot = degToRad(5) + -rot2;

		bone8.zRot = degToRad(120);
		bone11.zRot = degToRad(120);
		bone15.zRot = degToRad(120);
		bone19.zRot = degToRad(-120);
		bone23.zRot = degToRad(-120);
		bone27.zRot = degToRad(-120);

		if (landed) {
			float mod = entity.isPassenger() ? 30 : 90;

			bone8.zRot += -degToRad(mod);
			bone11.zRot += -degToRad(mod);
			bone15.zRot += -degToRad(mod);
			bone19.zRot += degToRad(mod);
			bone23.zRot += degToRad(mod);
			bone27.zRot += degToRad(mod);
		}
	}

	private float degToRad(float deg) {
		return (float)Math.PI / 180f * deg;
	}
}
