package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArcflower extends ModelBase {
	ModelRenderer body5;
	ModelRenderer body9;

	public ModelArcflower() {
		textureWidth = 64;
		textureHeight = 64;

		body5 = new ModelRenderer(this, 23, 35);
		body5.addBox(-7.5F, -17.0F, -7.0F, 16, 16, 0);
		body5.setRotationPoint(5.0F, 25.0F, 5.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.7853982F, 0.0F);
		body9 = new ModelRenderer(this, 23, 35);
		body9.addBox(-8.5F, -17.0F, -7.0F, 16, 16, 0);
		body9.setRotationPoint(-4.0F, 25.0F, 5.0F);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, -0.7853982F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body5.render(par7);
		body9.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
	}
}
