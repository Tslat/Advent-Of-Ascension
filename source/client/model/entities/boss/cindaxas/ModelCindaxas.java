package net.tslat.aoa3.client.model.entities.boss.cindaxas;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCindaxas extends ModelBase {
	private final ModelRenderer root;

	public ModelCindaxas() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		ModelRenderer LLEG = new ModelRenderer(this);
		LLEG.setRotationPoint(0.0F, -16.0F, 0.0F);
		setRotationAngle(LLEG, -1.1345F, -0.4363F, 0.0F);
		root.addChild(LLEG);
		LLEG.cubeList.add(new ModelBox(LLEG, 100, 20, 1.0F, 0.0F, -3.0F, 6, 10, 6, 0.0F, true));
		LLEG.cubeList.add(new ModelBox(LLEG, 103, 21, 2.0F, 10.0F, -2.0F, 4, 1, 5, 0.0F, true));

		ModelRenderer LKNEE = new ModelRenderer(this);
		LKNEE.setRotationPoint(4.0F, 9.0F, 0.0F);
		setRotationAngle(LKNEE, 0.2618F, 0.0F, 0.0F);
		LLEG.addChild(LKNEE);
		LKNEE.cubeList.add(new ModelBox(LKNEE, 100, 36, -2.0F, -3.0F, 2.0F, 4, 4, 7, 0.0F, true));

		ModelRenderer bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, -1.0472F, 0.0F, 0.0F);
		LKNEE.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 99, 47, -2.5F, -8.4282F, 1.4019F, 5, 4, 8, 0.0F, true));
		bone8.cubeList.add(new ModelBox(bone8, 1, 56, -3.0F, -6.276F, 7.7488F, 6, 3, 3, 0.0F, false));

		ModelRenderer bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone12, 0.3491F, 0.0F, 0.0F);
		bone8.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 55, -3.0F, -5.4282F, 8.4019F, 6, 5, 4, 0.0F, true));
		bone12.cubeList.add(new ModelBox(bone12, 3, 59, -3.0F, -0.4021F, 11.221F, 6, 1, 1, 0.0F, false));
		bone12.cubeList.add(new ModelBox(bone12, 3, 59, -3.0F, -0.4282F, 11.4019F, 6, 1, 1, 0.0F, false));

		ModelRenderer RLEG = new ModelRenderer(this);
		RLEG.setRotationPoint(0.0F, -16.0F, 0.0F);
		setRotationAngle(RLEG, -1.1345F, 0.4363F, 0.0F);
		root.addChild(RLEG);
		RLEG.cubeList.add(new ModelBox(RLEG, 100, 20, -7.0F, 0.0F, -3.0F, 6, 10, 6, 0.0F, false));
		RLEG.cubeList.add(new ModelBox(RLEG, 103, 21, -6.0F, 10.0F, -2.0F, 4, 1, 5, 0.0F, false));

		ModelRenderer RKNEE = new ModelRenderer(this);
		RKNEE.setRotationPoint(-4.0F, 9.0F, 0.0F);
		setRotationAngle(RKNEE, 0.2618F, 0.0F, 0.0F);
		RLEG.addChild(RKNEE);
		RKNEE.cubeList.add(new ModelBox(RKNEE, 100, 36, -2.0F, -3.0F, 2.0F, 4, 4, 7, 0.0F, false));

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone14, -1.0472F, 0.0F, 0.0F);
		RKNEE.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 99, 47, -2.5F, -8.4282F, 1.4019F, 5, 4, 8, 0.0F, false));
		bone14.cubeList.add(new ModelBox(bone14, 1, 56, -3.0F, -6.276F, 7.7488F, 6, 3, 3, 0.0F, false));

		ModelRenderer bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone15, 0.3491F, 0.0F, 0.0F);
		bone14.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 0, 55, -3.0F, -5.4282F, 8.4019F, 6, 5, 4, 0.0F, false));
		bone15.cubeList.add(new ModelBox(bone15, 3, 59, -3.0F, -0.4021F, 11.221F, 6, 1, 1, 0.0F, false));
		bone15.cubeList.add(new ModelBox(bone15, 3, 59, -3.0F, -0.4282F, 11.4019F, 6, 1, 1, 0.0F, false));

		ModelRenderer BODY = new ModelRenderer(this);
		BODY.setRotationPoint(0.0F, -13.0F, 0.0F);
		setRotationAngle(BODY, 0.0873F, 0.0F, 0.0F);
		root.addChild(BODY);
		BODY.cubeList.add(new ModelBox(BODY, 64, 0, -5.0F, -19.0F, -3.0F, 10, 17, 8, 0.0F, false));
		BODY.cubeList.add(new ModelBox(BODY, 0, 64, 4.0F, -21.0F, -3.99F, 7, 8, 8, 0.0F, false));
		BODY.cubeList.add(new ModelBox(BODY, 0, 64, -11.0F, -21.0F, -3.99F, 7, 8, 8, 0.0F, false));
		BODY.cubeList.add(new ModelBox(BODY, 64, 54, -5.0F, -22.0F, -4.0F, 10, 3, 9, 0.0F, false));

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone4, 0.0873F, 0.0F, 0.0F);
		BODY.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 64, 25, -6.0F, -32.0F, -2.0F, 12, 10, 6, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 64, 41, -4.0F, -22.0F, -1.75F, 8, 7, 6, 0.0F, false));

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone6, 0.0F, -0.0873F, -0.0873F);
		bone4.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 30, 66, 3.0F, -32.0F, -3.0F, 7, 8, 6, 0.0F, true));

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0873F, 0.0873F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 30, 66, -10.0F, -32.0F, -3.0F, 7, 8, 6, 0.0F, false));

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.1745F);
		BODY.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 100, 0, -3.0F, -29.0F, -3.01F, 3, 13, 7, 0.0F, false));

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.1745F);
		BODY.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 100, 0, 0.0F, -29.0F, -3.01F, 3, 13, 7, 0.0F, true));

		ModelRenderer TAIL = new ModelRenderer(this);
		TAIL.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(TAIL, 0.4363F, 0.0F, 0.0F);
		BODY.addChild(TAIL);
		TAIL.cubeList.add(new ModelBox(TAIL, 0, 80, -3.0F, -0.3615F, -3.1415F, 6, 10, 7, 0.0F, false));

		ModelRenderer bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone17, 0.5236F, 0.0F, 0.0F);
		TAIL.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 2, 81, -2.5F, 6.7764F, -7.5399F, 5, 8, 6, 0.0F, false));

		ModelRenderer bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone18, 0.3491F, 0.0F, 0.0F);
		bone17.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 4, 82, -2.0F, 11.3065F, -12.139F, 4, 7, 5, 0.0F, false));

		ModelRenderer bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone19, 0.0873F, 0.0F, 0.0F);
		bone18.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 6, 83, -1.5F, 17.1788F, -13.6883F, 3, 5, 4, 0.0F, false));

		ModelRenderer bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone20, 0.0873F, 0.0F, 0.0F);
		bone19.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 8, 84, -1.0F, 20.9014F, -15.5692F, 2, 6, 3, 0.0F, false));

		ModelRenderer RARM = new ModelRenderer(this);
		RARM.setRotationPoint(0.0F, -18.0F, 0.0F);
		BODY.addChild(RARM);

		ModelRenderer bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 31.0F, 0.0F);
		setRotationAngle(bone24, 0.0F, 0.0F, -0.4363F);
		RARM.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 0, 16, 0.0F, -36.0F, -4.0F, 7, 9, 8, 0.0F, true));

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(4.0F, -34.0F, 0.0F);
		setRotationAngle(bone2, -0.0873F, 0.0F, -0.2618F);
		bone24.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 47, -3.0F, -7.0F, 0.5F, 3, 5, 3, 0.0F, true));
		bone2.cubeList.add(new ModelBox(bone2, 56, 60, -2.75F, -9.0F, 1.25F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(4.0F, -34.0F, 0.0F);
		setRotationAngle(bone7, -0.2618F, 0.0F, 0.1745F);
		bone24.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 47, -1.5F, -6.0F, -1.0F, 3, 5, 3, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 56, 60, -1.25F, -8.0F, -0.25F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(4.0F, -34.0F, 0.0F);
		setRotationAngle(bone13, 0.0873F, 0.0F, -0.6109F);
		bone24.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 47, -3.25F, -5.5F, -1.75F, 3, 5, 3, 0.0F, true));
		bone13.cubeList.add(new ModelBox(bone13, 56, 60, -3.0F, -7.5F, -1.0F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(4.0F, -34.0F, 0.0F);
		setRotationAngle(bone22, 0.2618F, 0.0F, 0.0F);
		bone24.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 0, 47, -1.75F, -4.0F, -2.0F, 3, 5, 3, 0.0F, true));
		bone22.cubeList.add(new ModelBox(bone22, 56, 60, -1.25F, -6.0F, -1.25F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-5.3F, 3.2F, 0.0F);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.6981F);
		RARM.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 56, 66, -6.0F, 0.0F, -3.0F, 6, 8, 6, 0.0F, false));

		ModelRenderer bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-4.0F, 0.0F, -2.0F);
		bone11.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 85, 66, -1.0F, 4.0F, 0.0F, 4, 7, 4, 0.0F, false));

		ModelRenderer RARM2 = new ModelRenderer(this);
		RARM2.setRotationPoint(2.15F, 10.45F, 1.6F);
		setRotationAngle(RARM2, -1.0472F, 0.0F, -0.4363F);
		bone21.addChild(RARM2);
		RARM2.cubeList.add(new ModelBox(RARM2, 101, 68, -2.4F, -0.9F, -2.0F, 4, 6, 3, 0.0F, false));

		ModelRenderer bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(2.0F, -11.0F, -3.0F);
		setRotationAngle(bone23, -0.3491F, 0.0F, 0.0F);
		RARM2.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 28, 51, -4.9F, 13.761F, 6.2653F, 5, 5, 3, 0.0F, false));

		ModelRenderer bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-2.35F, 16.0F, 8.0F);
		setRotationAngle(bone25, 0.0F, 0.5236F, 0.0F);
		bone23.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 29, 59, -2.55F, -0.739F, -4.2347F, 2, 2, 3, 0.0F, true));

		ModelRenderer bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone28, 0.0F, -1.1345F, 0.0F);
		bone25.addChild(bone28);
		bone28.cubeList.add(new ModelBox(bone28, 56, 60, -4.8F, -0.239F, -2.7347F, 1, 1, 3, 0.0F, false));

		ModelRenderer bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(-2.35F, 16.0F, 8.0F);
		setRotationAngle(bone26, 0.0F, -0.5236F, 0.0F);
		bone23.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 29, 59, 0.7F, -0.739F, -3.9847F, 2, 2, 3, 0.0F, false));

		ModelRenderer bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone29, 0.0F, 1.1345F, 0.0F);
		bone26.addChild(bone29);
		bone29.cubeList.add(new ModelBox(bone29, 56, 60, 3.7F, -0.239F, -2.4847F, 1, 1, 3, 0.0F, false));

		ModelRenderer bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(-2.35F, 16.0F, 8.0F);
		setRotationAngle(bone27, 0.4363F, 0.0F, 0.0F);
		bone23.addChild(bone27);
		bone27.cubeList.add(new ModelBox(bone27, 29, 59, -1.05F, 1.011F, -3.9847F, 2, 2, 3, 0.0F, true));

		ModelRenderer bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone30, -1.1345F, 0.0F, 0.0F);
		bone27.addChild(bone30);
		bone30.cubeList.add(new ModelBox(bone30, 56, 60, -0.55F, 3.761F, -2.4847F, 1, 1, 3, 0.0F, false));

		ModelRenderer LARM = new ModelRenderer(this);
		LARM.setRotationPoint(0.0F, -18.0F, 0.0F);
		BODY.addChild(LARM);

		ModelRenderer bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(0.0F, 31.0F, 0.0F);
		setRotationAngle(bone33, 0.0F, 0.0F, 0.4363F);
		LARM.addChild(bone33);
		bone33.cubeList.add(new ModelBox(bone33, 0, 16, -7.0F, -36.0F, -4.0F, 7, 9, 8, 0.0F, false));

		ModelRenderer bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(-4.0F, -34.0F, 0.0F);
		setRotationAngle(bone31, -0.0873F, 0.0F, 0.2618F);
		bone33.addChild(bone31);
		bone31.cubeList.add(new ModelBox(bone31, 0, 47, 0.75F, -7.0F, 0.5F, 3, 5, 3, 0.0F, true));
		bone31.cubeList.add(new ModelBox(bone31, 56, 60, 1.25F, -9.0F, 1.25F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(-4.0F, -34.0F, 0.0F);
		setRotationAngle(bone32, -0.2618F, 0.0F, -0.1745F);
		bone33.addChild(bone32);
		bone32.cubeList.add(new ModelBox(bone32, 0, 47, -1.5F, -6.0F, 0.0F, 3, 5, 3, 0.0F, false));
		bone32.cubeList.add(new ModelBox(bone32, 56, 60, -0.75F, -8.0F, 0.75F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone36 = new ModelRenderer(this);
		bone36.setRotationPoint(-4.0F, -34.0F, 0.0F);
		setRotationAngle(bone36, 0.0873F, 0.0F, 0.0873F);
		bone33.addChild(bone36);
		bone36.cubeList.add(new ModelBox(bone36, 0, 47, -1.25F, -4.5F, -1.75F, 3, 5, 3, 0.0F, true));
		bone36.cubeList.add(new ModelBox(bone36, 56, 60, -0.5F, -6.5F, -1.0F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone44 = new ModelRenderer(this);
		bone44.setRotationPoint(-4.0F, -34.0F, 0.0F);
		setRotationAngle(bone44, 0.2618F, 0.0F, 0.4363F);
		bone33.addChild(bone44);
		bone44.cubeList.add(new ModelBox(bone44, 0, 47, 0.25F, -6.0F, -1.0F, 3, 5, 3, 0.0F, false));
		bone44.cubeList.add(new ModelBox(bone44, 56, 60, 1.0F, -8.0F, -0.5F, 2, 2, 2, 0.0F, false));

		ModelRenderer bone34 = new ModelRenderer(this);
		bone34.setRotationPoint(5.3F, 3.2F, 0.0F);
		setRotationAngle(bone34, 0.0F, 0.0F, -0.6981F);
		LARM.addChild(bone34);
		bone34.cubeList.add(new ModelBox(bone34, 56, 66, 0.0F, 0.0F, -3.0F, 6, 8, 6, 0.0F, false));

		ModelRenderer bone35 = new ModelRenderer(this);
		bone35.setRotationPoint(4.0F, 0.0F, -2.0F);
		bone34.addChild(bone35);
		bone35.cubeList.add(new ModelBox(bone35, 85, 66, -3.0F, 4.0F, 0.0F, 4, 7, 4, 0.0F, false));

		ModelRenderer LARM2 = new ModelRenderer(this);
		LARM2.setRotationPoint(-2.15F, 10.45F, 1.6F);
		setRotationAngle(LARM2, -1.0472F, 0.0F, 0.4363F);
		bone35.addChild(LARM2);
		LARM2.cubeList.add(new ModelBox(LARM2, 101, 68, -1.6F, -0.9F, -2.0F, 4, 6, 3, 0.0F, true));

		ModelRenderer bone37 = new ModelRenderer(this);
		bone37.setRotationPoint(-2.0F, -11.0F, -3.0F);
		setRotationAngle(bone37, -0.3491F, 0.0F, 0.0F);
		LARM2.addChild(bone37);
		bone37.cubeList.add(new ModelBox(bone37, 28, 51, -0.1F, 13.761F, 6.2653F, 5, 5, 3, 0.0F, true));

		ModelRenderer bone38 = new ModelRenderer(this);
		bone38.setRotationPoint(2.35F, 16.0F, 8.0F);
		setRotationAngle(bone38, 0.0F, -0.5236F, 0.0F);
		bone37.addChild(bone38);
		bone38.cubeList.add(new ModelBox(bone38, 29, 59, 0.55F, -0.739F, -4.2347F, 2, 2, 3, 0.0F, false));

		ModelRenderer bone39 = new ModelRenderer(this);
		bone39.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone39, 0.0F, 1.1345F, 0.0F);
		bone38.addChild(bone39);
		bone39.cubeList.add(new ModelBox(bone39, 56, 60, 3.8F, -0.239F, -2.7347F, 1, 1, 3, 0.0F, true));

		ModelRenderer bone40 = new ModelRenderer(this);
		bone40.setRotationPoint(2.35F, 16.0F, 8.0F);
		setRotationAngle(bone40, 0.0F, 0.5236F, 0.0F);
		bone37.addChild(bone40);
		bone40.cubeList.add(new ModelBox(bone40, 29, 59, -2.7F, -0.739F, -3.9847F, 2, 2, 3, 0.0F, false));

		ModelRenderer bone41 = new ModelRenderer(this);
		bone41.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone41, 0.0F, -1.1345F, 0.0F);
		bone40.addChild(bone41);
		bone41.cubeList.add(new ModelBox(bone41, 56, 60, -4.7F, -0.239F, -2.4847F, 1, 1, 3, 0.0F, true));

		ModelRenderer bone42 = new ModelRenderer(this);
		bone42.setRotationPoint(2.35F, 16.0F, 8.0F);
		setRotationAngle(bone42, 0.4363F, 0.0F, 0.0F);
		bone37.addChild(bone42);
		bone42.cubeList.add(new ModelBox(bone42, 29, 59, -0.95F, 1.011F, -3.9847F, 2, 2, 3, 0.0F, true));

		ModelRenderer bone43 = new ModelRenderer(this);
		bone43.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone43, -1.1345F, 0.0F, 0.0F);
		bone42.addChild(bone43);
		bone43.cubeList.add(new ModelBox(bone43, 56, 60, -0.45F, 3.761F, -2.4847F, 1, 1, 3, 0.0F, true));

		ModelRenderer HEAD = new ModelRenderer(this);
		HEAD.setRotationPoint(0.0F, -21.0F, 0.0F);
		BODY.addChild(HEAD);
		HEAD.cubeList.add(new ModelBox(HEAD, 22, 13, -4.0F, -4.0F, -3.0F, 8, 3, 6, 0.0F, true));

		ModelRenderer JAW = new ModelRenderer(this);
		JAW.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(JAW, 0.6109F, 0.0F, 0.0F);
		HEAD.addChild(JAW);
		JAW.cubeList.add(new ModelBox(JAW, 30, 0, -3.5F, -3.0F, -8.5F, 7, 3, 10, 0.0F, false));
		JAW.cubeList.add(new ModelBox(JAW, 35, 26, 2.5F, -4.0F, -3.5F, 1, 4, 3, 0.0F, true));
		JAW.cubeList.add(new ModelBox(JAW, 35, 26, -3.5F, -4.0F, -3.5F, 1, 4, 3, 0.0F, false));
		JAW.cubeList.add(new ModelBox(JAW, 4, 33, -3.51F, -3.5F, -8.5F, 1, 1, 10, 0.0F, false));
		JAW.cubeList.add(new ModelBox(JAW, 19, 52, -2.5F, -3.5F, -8.51F, 5, 1, 1, 0.0F, false));
		JAW.cubeList.add(new ModelBox(JAW, 4, 33, 2.51F, -3.5F, -8.5F, 1, 1, 10, 0.0F, true));

		ModelRenderer bone57 = new ModelRenderer(this);
		bone57.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone57, -0.1745F, 0.0F, -0.1745F);
		JAW.addChild(bone57);
		bone57.cubeList.add(new ModelBox(bone57, 28, 44, 2.5F, -3.0F, -9.5F, 2, 5, 2, 0.0F, true));

		ModelRenderer bone58 = new ModelRenderer(this);
		bone58.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone58, -0.1745F, 0.0F, 0.1745F);
		JAW.addChild(bone58);
		bone58.cubeList.add(new ModelBox(bone58, 28, 44, -4.5F, -3.0F, -9.5F, 2, 5, 2, 0.0F, false));

		ModelRenderer bone46 = new ModelRenderer(this);
		bone46.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone46, -0.0873F, 0.0F, 0.0F);
		HEAD.addChild(bone46);
		bone46.cubeList.add(new ModelBox(bone46, 0, 1, -3.0F, -4.0F, -8.5F, 6, 3, 9, 0.0F, false));
		bone46.cubeList.add(new ModelBox(bone46, 34, 34, 3.01F, -4.0F, -6.7F, 1, 3, 4, 0.0F, true));
		bone46.cubeList.add(new ModelBox(bone46, 34, 34, -3.99F, -4.0F, -6.7F, 1, 3, 4, 0.0F, false));

		ModelRenderer bone47 = new ModelRenderer(this);
		bone47.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone47, -0.2618F, 0.0F, 0.0F);
		bone46.addChild(bone47);
		bone47.cubeList.add(new ModelBox(bone47, 44, 26, -3.5F, -2.6637F, -9.2456F, 7, 3, 3, 0.0F, false));
		bone47.cubeList.add(new ModelBox(bone47, 16, 39, -3.5F, -1.6637F, -9.3456F, 7, 3, 1, 0.0F, false));
		bone47.cubeList.add(new ModelBox(bone47, 35, 26, -4.01F, -1.6637F, -7.7456F, 1, 3, 2, 0.0F, false));
		bone47.cubeList.add(new ModelBox(bone47, 35, 26, 2.99F, -1.6637F, -7.7456F, 1, 3, 2, 0.0F, true));
		bone47.cubeList.add(new ModelBox(bone47, 0, 0, 2.49F, -3.1637F, -9.3956F, 1, 3, 1, 0.0F, true));
		bone47.cubeList.add(new ModelBox(bone47, 0, 0, -3.51F, -3.1637F, -9.3956F, 1, 3, 1, 0.0F, false));

		ModelRenderer bone48 = new ModelRenderer(this);
		bone48.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone48, 0.4363F, 0.0F, 0.0F);
		bone46.addChild(bone48);
		bone48.cubeList.add(new ModelBox(bone48, 36, 33, -3.0F, -6.0F, -3.5F, 6, 4, 8, 0.0F, false));

		ModelRenderer bone49 = new ModelRenderer(this);
		bone49.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone49, 0.0F, -0.3491F, 0.0F);
		bone48.addChild(bone49);
		bone49.cubeList.add(new ModelBox(bone49, 0, 34, -3.3321F, -6.0F, -0.3835F, 2, 3, 5, 0.0F, true));

		ModelRenderer bone51 = new ModelRenderer(this);
		bone51.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone51, 0.4363F, 0.0F, 0.0F);
		bone49.addChild(bone51);
		bone51.cubeList.add(new ModelBox(bone51, 39, 56, 3.52F, -4.8485F, 2.7997F, 3, 3, 5, 0.0F, true));
		bone51.cubeList.add(new ModelBox(bone51, 50, 53, 7.7161F, -4.3495F, 4.7317F, 2, 2, 5, 0.0F, true));

		ModelRenderer bone53 = new ModelRenderer(this);
		bone53.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone53, 0.0F, 2.0944F, 0.0F);
		bone51.addChild(bone53);
		bone53.cubeList.add(new ModelBox(bone53, 48, 45, -10.5147F, -4.3485F, -0.8514F, 2, 2, 6, 0.0F, false));

		ModelRenderer bone54 = new ModelRenderer(this);
		bone54.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone54, 0.0F, 0.2618F, 0.0F);
		bone53.addChild(bone54);
		bone54.cubeList.add(new ModelBox(bone54, 56, 60, -13.6103F, -3.8485F, -2.8696F, 1, 1, 3, 0.0F, false));

		ModelRenderer bone50 = new ModelRenderer(this);
		bone50.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone50, 0.0F, 0.3491F, 0.0F);
		bone48.addChild(bone50);
		bone50.cubeList.add(new ModelBox(bone50, 0, 34, 1.3321F, -6.0F, -0.3835F, 2, 3, 5, 0.0F, false));

		ModelRenderer bone52 = new ModelRenderer(this);
		bone52.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone52, 0.4363F, 0.0F, 0.0F);
		bone50.addChild(bone52);
		bone52.cubeList.add(new ModelBox(bone52, 39, 56, -6.52F, -4.8485F, 2.7997F, 3, 3, 5, 0.0F, false));
		bone52.cubeList.add(new ModelBox(bone52, 50, 53, -9.7839F, -4.3495F, 4.7317F, 2, 2, 5, 0.0F, false));

		ModelRenderer bone55 = new ModelRenderer(this);
		bone55.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone55, 0.0F, -2.0944F, 0.0F);
		bone52.addChild(bone55);
		bone55.cubeList.add(new ModelBox(bone55, 48, 45, 8.5147F, -4.3485F, -0.8514F, 2, 2, 6, 0.0F, true));

		ModelRenderer bone56 = new ModelRenderer(this);
		bone56.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone56, 0.0F, -0.2618F, 0.0F);
		bone55.addChild(bone56);
		bone56.cubeList.add(new ModelBox(bone56, 56, 60, 12.6103F, -3.8485F, -2.8696F, 1, 1, 3, 0.0F, true));

		ModelRenderer bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.2618F, 0.0F, 0.0F);
		bone48.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 44, 16, -2.0F, -6.3F, -0.5F, 4, 3, 6, 0.0F, false));

		ModelRenderer bone45 = new ModelRenderer(this);
		bone45.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone45, -0.1745F, 0.0F, 0.0F);
		BODY.addChild(bone45);
		bone45.cubeList.add(new ModelBox(bone45, 26, 80, -6.0F, -34.0F, -2.0F, 12, 13, 2, 0.0F, false));

		ModelRenderer bone59 = new ModelRenderer(this);
		bone59.setRotationPoint(0.0F, 13.0F, 0.0F);
		setRotationAngle(bone59, -0.2618F, 0.0F, 0.0F);
		BODY.addChild(bone59);
		bone59.cubeList.add(new ModelBox(bone59, 28, 80, -5.0F, -32.75F, -3.75F, 10, 13, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}