package net.tslat.aoa3.client.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class SkillHelmetModel extends BipedModel<LivingEntity> {
	private final ResourceLocation texturePath;

	public SkillHelmetModel(ResourceLocation texturePath) {
		super(0.5f);

		this.texturePath = texturePath;
	}

	public ResourceLocation getTexturePath() {
		return this.texturePath;
	}

	protected void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
