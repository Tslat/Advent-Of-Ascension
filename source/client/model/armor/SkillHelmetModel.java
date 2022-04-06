package net.tslat.aoa3.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class SkillHelmetModel extends Model implements HeadedModel {
	private final ModelPart head;

	public SkillHelmetModel(ModelPart meshDefRoot) {
		super(RenderType::entityCutoutNoCull);

		this.head = meshDefRoot.getChild("root");
	}

	public void setProperties(HumanoidModel<?> baseModel) {
		this.head.copyFrom(baseModel.getHead());
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getHead() {
		return this.head;
	}
}