package net.tslat.aoa3.client.model.entities.passive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.passive.EntityMeganeuropsis;

public class ModelMeganeuropsis extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer bone6;
	private final ModelRenderer tail;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer leg;
	private final ModelRenderer bone3;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer leg2;
	private final ModelRenderer bone2;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer leg3;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer leg4;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer leg5;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer leg6;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer wing1;
	private final ModelRenderer wing2;
	private final ModelRenderer wing3;
	private final ModelRenderer wing4;

	public ModelMeganeuropsis() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 19.0F, 0.0F);
		root.cubeList.add(new ModelBox(root, 14, 7, -1.0F, -1.0F, -4.0F, 2, 2, 2, 0.1F, false));
		root.cubeList.add(new ModelBox(root, 15, 11, 0.75F, -1.0F, -4.15F, 1, 2, 2, 0.0F, false));
		root.cubeList.add(new ModelBox(root, 15, 11, -1.75F, -1.0F, -4.15F, 1, 2, 2, 0.0F, true));
		root.cubeList.add(new ModelBox(root, 0, 7, -1.0F, -1.0F, -2.0F, 2, 2, 2, 0.15F, false));
		root.cubeList.add(new ModelBox(root, 0, 7, -1.0F, -1.0F, 0.0F, 2, 2, 2, 0.1F, true));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, -1.0F, -4.0F);
		setRotation(bone6, 0.3491F, 0.0F, 0.0F);
		root.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 22, 7, -1.0F, -0.1282F, -0.0598F, 2, 1, 2, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 22, 10, -1.0F, 1.4693F, -0.6412F, 2, 1, 2, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotation(tail, 0.0349F, 0.0F, 0.0F);
		root.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.9915F, -1.0961F, 2, 2, 2, -0.05F, false));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.9833F, 0.6567F, 2, 2, 2, -0.15F, true));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.9697F, 2.3097F, 2, 2, 2, -0.2F, false));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.951F, 3.8628F, 2, 2, 2, -0.25F, true));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.927F, 5.316F, 2, 2, 2, -0.3F, false));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.8952F, 6.6195F, 2, 2, 2, -0.35F, true));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.8581F, 7.8231F, 2, 2, 2, -0.4F, false));
		tail.cubeList.add(new ModelBox(tail, 0, 7, -1.0F, -0.8158F, 8.9268F, 2, 2, 2, -0.45F, true));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.25F, 10.0F);
		setRotation(bone4, 0.0F, -0.4363F, 0.0F);
		tail.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 8, 7, -0.6848F, -0.4992F, -0.0609F, 1, 1, 2, -0.25F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.25F, 10.0F);
		setRotation(bone5, 0.0F, 0.4363F, 0.0F);
		tail.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 8, 7, -0.3152F, -0.4992F, -0.0609F, 1, 1, 2, -0.25F, false));

		leg = new ModelRenderer(this);
		leg.setRotationPoint(1.15F, 1.5F, -0.55F);
		root.addChild(leg);
		leg.cubeList.add(new ModelBox(leg, 8, 11, -0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.15F, -1.1F, 0.5F);
		setRotation(bone3, 0.0F, 0.0F, -1.1345F);
		leg.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 15, -1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone7, 0.0F, 0.0F, 1.7453F);
		bone3.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 4, 15, -0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(1.4585F, -0.3393F, 0.0F);
		setRotation(bone8, 0.0F, 0.0F, 2.0944F);
		bone7.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 11, -0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(2.3222F, 0.2903F, 0.0F);
		setRotation(bone9, 0.0F, 0.0F, 2.0944F);
		bone8.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 13, -1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(0.6F, 1.6F, -1.3F);
		setRotation(leg2, 0.0F, 0.6981F, 0.0F);
		root.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 8, 11, -0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.15F, -1.1F, 0.5F);
		setRotation(bone2, 0.0F, 0.0F, -1.1345F);
		leg2.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 15, -1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone10, 0.0F, 0.0F, 1.7453F);
		bone2.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 4, 15, -0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(1.4585F, -0.3393F, 0.0F);
		setRotation(bone11, 0.0F, 0.0F, 2.0944F);
		bone10.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 11, -0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(2.3222F, 0.2903F, 0.0F);
		setRotation(bone12, 0.0F, 0.0F, 2.0944F);
		bone11.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 13, -1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(1.2F, 1.6F, 0.4F);
		setRotation(leg3, 0.0F, -0.6981F, 0.0F);
		root.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 8, 11, -0.75F, -1.0F, 0.0F, 1, 1, 1, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.15F, -1.1F, 0.5F);
		setRotation(bone13, 0.0F, 0.0F, -1.1345F);
		leg3.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 15, -1.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-0.7642F, 1.6963F, 0.0F);
		setRotation(bone14, 0.0F, 0.0F, 1.7453F);
		bone13.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 4, 15, -0.1915F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(1.4585F, -0.3393F, 0.0F);
		setRotation(bone15, 0.0F, 0.0F, 2.0944F);
		bone14.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 0, 11, -0.3843F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(2.3222F, 0.2903F, 0.0F);
		setRotation(bone16, 0.0F, 0.0F, 2.0944F);
		bone15.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 0, 13, -1.6934F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-1.3F, 1.5F, -0.55F);
		root.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 8, 11, -0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-0.15F, -1.1F, 0.5F);
		setRotation(bone17, 0.0F, 0.0F, 1.1345F);
		leg4.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 0, 15, 0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.7642F, 1.6963F, 0.0F);
		setRotation(bone18, 0.0F, 0.0F, -1.7453F);
		bone17.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 4, 15, -1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone19, 0.0F, 0.0F, -2.0944F);
		bone18.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 0, 11, -2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone20, 0.0F, 0.0F, -2.0944F);
		bone19.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 0, 13, -0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true));

		leg5 = new ModelRenderer(this);
		leg5.setRotationPoint(-0.75F, 1.6F, -1.3F);
		setRotation(leg5, 0.0F, -0.6981F, 0.0F);
		root.addChild(leg5);
		leg5.cubeList.add(new ModelBox(leg5, 8, 11, -0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-0.15F, -1.1F, 0.5F);
		setRotation(bone21, 0.0F, 0.0F, 1.1345F);
		leg5.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 0, 15, 0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.7642F, 1.6963F, 0.0F);
		setRotation(bone22, 0.0F, 0.0F, -1.7453F);
		bone21.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 4, 15, -1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone23, 0.0F, 0.0F, -2.0944F);
		bone22.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 0, 11, -2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone24, 0.0F, 0.0F, -2.0944F);
		bone23.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 0, 13, -0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true));

		leg6 = new ModelRenderer(this);
		leg6.setRotationPoint(-1.35F, 1.6F, 0.4F);
		setRotation(leg6, 0.0F, 0.6981F, 0.0F);
		root.addChild(leg6);
		leg6.cubeList.add(new ModelBox(leg6, 8, 11, -0.25F, -1.0F, 0.0F, 1, 1, 1, 0.0F, true));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-0.15F, -1.1F, 0.5F);
		setRotation(bone25, 0.0F, 0.0F, 1.1345F);
		leg6.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 0, 15, 0.0F, 0.0F, -0.5F, 1, 2, 1, -0.1F, true));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.7642F, 1.6963F, 0.0F);
		setRotation(bone26, 0.0F, 0.0F, -1.7453F);
		bone25.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 4, 15, -1.8085F, -0.8393F, -0.5F, 2, 1, 1, -0.2F, true));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(-1.4585F, -0.3393F, 0.0F);
		setRotation(bone27, 0.0F, 0.0F, -2.0944F);
		bone26.addChild(bone27);
		bone27.cubeList.add(new ModelBox(bone27, 0, 11, -2.6157F, -0.4059F, -0.5F, 3, 1, 1, -0.3F, true));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(-2.3222F, 0.2903F, 0.0F);
		setRotation(bone28, 0.0F, 0.0F, -2.0944F);
		bone27.addChild(bone28);
		bone28.cubeList.add(new ModelBox(bone28, 0, 13, -0.3066F, -0.2962F, -0.5F, 2, 1, 1, -0.35F, true));

		wing1 = new ModelRenderer(this);
		wing1.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotation(wing1, 0.0F, 0.1745F, 0.0873F);
		root.addChild(wing1);
		wing1.cubeList.add(new ModelBox(wing1, 0, 22, -12.25F, -0.1F, 0.25F, 12, 0, 4, 0.0F, false));

		wing2 = new ModelRenderer(this);
		wing2.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotation(wing2, 0.0F, -0.1745F, -0.0873F);
		root.addChild(wing2);
		wing2.cubeList.add(new ModelBox(wing2, 0, 22, 0.25F, -0.1F, 0.25F, 12, 0, 4, 0.0F, true));

		wing3 = new ModelRenderer(this);
		wing3.setRotationPoint(0.0F, -1.25F, -1.25F);
		setRotation(wing3, 0.0F, -0.1745F, 0.0873F);
		root.addChild(wing3);
		wing3.cubeList.add(new ModelBox(wing3, 0, 18, -12.0472F, 0.1F, -0.75F, 12, 0, 4, 0.0F, false));

		wing4 = new ModelRenderer(this);
		wing4.setRotationPoint(0.0F, -1.25F, -1.25F);
		setRotation(wing4, 0.0F, 0.1745F, -0.0873F);
		root.addChild(wing4);
		wing4.cubeList.add(new ModelBox(wing4, 0, 18, 0.0472F, 0.099F, -0.75F, 12, 0, 4, 0.0F, true));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		root.render(scale);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		boolean landed = ((EntityMeganeuropsis)entity).isLanded();
		float modifier = landed ? 0.05f : 7.5f;
		float rot1 = -(MathHelper.sin(ageInTicks * modifier) * 0.4f) * 0.436332313f;
		float rot2 = -(MathHelper.sin(ageInTicks * modifier + ((float)Math.PI) / 2f) * 0.4f) * 0.436332313f;

		wing1.rotateAngleZ = degToRad(-5) + rot1;
		wing2.rotateAngleZ = degToRad(5) + -rot1;
		wing3.rotateAngleZ = degToRad(-5) + rot2;
		wing4.rotateAngleZ = degToRad(5) + -rot2;

		bone8.rotateAngleZ = degToRad(120);
		bone11.rotateAngleZ = degToRad(120);
		bone15.rotateAngleZ = degToRad(120);
		bone19.rotateAngleZ = degToRad(-120);
		bone23.rotateAngleZ = degToRad(-120);
		bone27.rotateAngleZ = degToRad(-120);

		if (landed) {
			float mod = entity.isRiding() ? 30 : 90;

			bone8.rotateAngleZ += -degToRad(mod);
			bone11.rotateAngleZ += -degToRad(mod);
			bone15.rotateAngleZ += -degToRad(mod);
			bone19.rotateAngleZ += degToRad(mod);
			bone23.rotateAngleZ += degToRad(mod);
			bone27.rotateAngleZ += degToRad(mod);
		}
	}

	private float degToRad(float deg) {
		return (float)Math.PI / 180f * deg;
	}
}
