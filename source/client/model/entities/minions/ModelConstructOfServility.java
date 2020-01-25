package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.tslat.aoa3.client.model.entities.animations.ModelAnimatable;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfServility extends ModelAnimatable {
	private final ModelRenderer root;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer r2;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;

	public ModelConstructOfServility() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotation(root, 0.1745F, 0.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 2.0F);
		setRotation(head, -0.1745F, 0.0F, 0.0F);
		root.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -4.6527F, -5.9696F, 6, 6, 6, 0.0F, true));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -2.6527F, -1.9696F);
		setRotation(bone, -1.1345F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 24, 2, -2.0F, -5.22F, -3.5031F, 4, 7, 3, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 16, 14, -4.0F, -22.0F, -4.0F, 8, 10, 8, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 6, 32, -5.0F, -12.0F, -5.0F, 10, 4, 10, 0.0F, true));

		r2 = new ModelRenderer(this);
		r2.setRotationPoint(0.0F, -5.0F, 0.0F);
		body.addChild(r2);
		r2.cubeList.add(new ModelBox(r2, 0, 31, 4.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false));
		r2.cubeList.add(new ModelBox(r2, 0, 31, -8.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true));
		r2.cubeList.add(new ModelBox(r2, 0, 31, -2.0F, -2.0F, 4.0F, 4, 4, 4, 0.0F, true));
		r2.cubeList.add(new ModelBox(r2, 0, 31, -2.0F, -2.0F, -8.0F, 4, 4, 4, 0.0F, true));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		setRotation(leftarm, 0.0873F, 0.0F, 0.3491F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 0, 16, -3.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(5.0F, -22.0F, 0.0F);
		setRotation(rightarm, 0.0873F, 0.0F, -0.3491F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 0, 16, -1.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, true));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		root.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r2.rotateAngleY = par3 * 0.335f;


		/*r1.rotateAngleY = par3 * 0.067f * 1.25f;
		r2.rotateAngleY = par3 * 0.067f * 1.25f;
		r3.rotateAngleY = par3 * 0.067f * 1.25f;
		r4.rotateAngleY = par3 * 0.067f * 1.25f;
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;*/
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}
