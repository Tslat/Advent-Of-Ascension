package net.tslat.aoa3.client.model.entities.projectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelCobblestoneProjectile extends ModelBase {
	private final ModelRenderer block;

	public ModelCobblestoneProjectile() {
		textureWidth = 64;
		textureHeight = 32;

		block = new ModelRenderer(this);
		block.setRotationPoint(0.0F, 16.0F, 0.0F);
		block.cubeList.add(new ModelBox(block, 0, 0, -8.0F, -8.0F, -8.0F, 16, 16, 16, -2.0F, true));
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		block.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		block.rotateAngleX += limbSwingAmount * 0.03f + 1;
		block.rotateAngleY += limbSwingAmount * 0.03f + 1;
		block.rotateAngleZ += limbSwingAmount * 0.03f + 1;
	}
}
