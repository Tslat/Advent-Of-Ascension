package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOccultBlock extends ModelBase {
	ModelRenderer block;

	public ModelOccultBlock() {
		textureWidth = 32;
		textureHeight = 32;
		block = new ModelRenderer(this, 0, 0).addBox(0, 0f, 0, 1, 1, 1);
		block.setTextureSize(32, 32);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		block.render(par7);
	}
}