package net.tslat.aoa3.client.model.entities.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPlayerCrown extends ModelBase {
	public ModelRenderer crown;

	public ModelPlayerCrown() {
		textureWidth = 1;
		textureHeight = 1;
		crown = new ModelRenderer(this, 0, 0);
		crown.setRotationPoint(0.0F, 0.0F, 0.0F);
		crown.addBox(-1.0F, -11.5f, -3.0F, 2, 1, 1, 0.0F);
		crown.addBox(-1.0F, -11.5f, 2.0F, 2, 1, 1, 0.0F);
		crown.addBox(-1.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		crown.addBox(-1.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		crown.addBox(-2.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		crown.addBox(-2.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		crown.addBox(-2.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		crown.addBox(-2.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		crown.addBox(-3.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
		crown.addBox(0.5F, -11.5f, -2.5F, 1, 1, 1, 0.0F);
		crown.addBox(0.5F, -11.5f, 1.5F, 1, 1, 1, 0.0F);
		crown.addBox(1.0F, -11.5f, -2.0F, 1, 1, 1, 0.0F);
		crown.addBox(1.0F, -11.5f, 1.0F, 1, 1, 1, 0.0F);
		crown.addBox(1.5F, -11.5f, -1.5F, 1, 1, 1, 0.0F);
		crown.addBox(1.5F, -11.5f, 0.5F, 1, 1, 1, 0.0F);
		crown.addBox(2.0F, -11.5f, -1.0F, 1, 1, 2, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float netHeadPitch, float scale) {
		crown.render(scale);
	}
}
